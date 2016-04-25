package controller;

import entity.Arccomments;
import entity.Archistory;
import entity.Arcincidents;
import entity.Comments;
import entity.History;
import entity.Incidents;
import entity.Typeincident;
import entity.Users;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.GetterBeanLocal;
import session.ManagementSystemLocal;

@WebServlet(name = "incident_controller", urlPatterns = {"/user/user_incident",
    "/user/new_incident", "/user/closed_incidents", "/user/done_incidents",
    "/sort_by_name", "/sort_by_date", "/sort_by_status", "/sort_by_spec",
    "/sort_by_name_closed", "/sort_by_dateo_closed", "/sort_by_status_closed",
    "/sort_by_spec_closed", "/sort_by_datec_closed", "/sort_by_id_closed", "/sort_by_id"})
public class incident_controller extends HttpServlet {

    @EJB
    private ManagementSystemLocal ms;

    @EJB
    private GetterBeanLocal gb;

    boolean filtered = false;
    String dateBeg = null;
    String dateEnd = null;
    String filterParam = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userPath = request.getServletPath();
        Users user = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", user);

        //сортировка =================================================================================================
        if ("/sort_by_id".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", gb.getOpenIncidents(user, "id"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_name".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", gb.getOpenIncidents(user, "name"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", gb.getOpenIncidents(user, "date"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", gb.getOpenIncidents(user, "status"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_spec".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", gb.getOpenIncidents(user, "spec"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }

        if ("/sort_by_id_closed".equals(userPath)) {
            getServletContext().setAttribute("action", userPath);
            filterOn(request, user, "id");
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_name_closed".equals(userPath)) {
            getServletContext().setAttribute("action", userPath);
            filterOn(request, user, "name");
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_dateo_closed".equals(userPath)) {
            getServletContext().setAttribute("action", userPath);
            filterOn(request, user, "dateo");
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_datec_closed".equals(userPath)) {
            getServletContext().setAttribute("action", userPath);
            filterOn(request, user, "datec");
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_closed".equals(userPath)) {
            getServletContext().setAttribute("action", userPath);
            filterOn(request, user, "status");
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_spec_closed".equals(userPath)) {
            getServletContext().setAttribute("action", userPath);
            filterOn(request, user, "spec");
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }

        //данные обращения =================================================================================================
        if ("/user/user_incident".equals(userPath)) {
            String answer = null;
            answer = checkAction(request);
            request.setAttribute("commento", 1);
            request.setAttribute("ihistory", 0);
            request.setAttribute("commenta", 0);

            String idIncident = null;
            idIncident = request.getParameter("id");
            Incidents incident = ms.findIncident(idIncident);
            if (incident == null) {
                Arcincidents arcincident = ms.findArcIncident(idIncident);

                List<Arccomments> arccomments = gb.getArccomments(arcincident);
                request.setAttribute("comments", arccomments);

                if (arcincident.getNew1().equals(1) && arcincident.getStatus().getId().equals(7)) {
                    ms.setNotNewIncident(null, arcincident);
                }

                //комментарии
                if (answer.equals("bComm")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                }

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("commento", 0);
                    List<Archistory> archistory = gb.getArchistory(arcincident);
                    request.setAttribute("allhistory", archistory);
                }

                request.setAttribute("incident", arcincident);
            } else {

                List<Comments> comments = gb.getComments(incident);
                request.setAttribute("comments", comments);

                if (incident.getNew1().equals(1) && incident.getStatus().getId().equals(4)) {
                    ms.setNotNewIncident(incident, null);
                }

                //редактировать
                if (answer.equals("Edit")) {
                    List<Typeincident> typs = gb.getTypesIncidentsForEdit(incident.getTypeIncident());
                    request.setAttribute("incident", incident);
                    request.setAttribute("typs", typs);
                    request.setAttribute("editincident", 1);
                    request.getRequestDispatcher("/WEB-INF/user/new_incident.jsp").forward(request, response);
                }

                //отменить
                if (answer.equals("Done")) {
                    ms.cancelIncident(incident, request.getParameter("textc"), request.getParameter("status"), false, null);
                    response.sendRedirect(request.getContextPath() + "/user");
                    return;
                }

                //подтвердить
                if (answer.equals("Accept")) {
                    ms.acceptIncident(incident);
                    response.sendRedirect(request.getContextPath() + "/user");
                    return;
                }

                //не подтверждать
                if (answer.equals("NoAccept")) {
                    request.setAttribute("commenta", 1);
                }

                //комментировать
                if (answer.equals("bCommGo")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                    ms.addComment(request.getParameter("textcomm"), user, incident);
                    response.sendRedirect(request.getContextPath() + "/user/user_incident?id=" + incident.getId());
                    return;
                }

                //комментарии
                if (answer.equals("bComm")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                }

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("commento", 0);
                    List<History> history = gb.getHistory(incident);
                    request.setAttribute("allhistory", history);
                }

                request.setAttribute("incident", incident);
            }

            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }

            //закрыть
            if (answer.equals("Close")) {
                request.setAttribute("commenta", 1);
            }
        }

        //новое обращения =================================================================================================
        if ("/user/new_incident".equals(userPath)) {
            String answer = null;
            answer = checkAction(request);

            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }

            //добавить
            if (answer.equals("Add")) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                String idInca = ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti, true, null, null);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }

            //редактировать
            if (answer.equals("Edit")) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                String idInc = ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti, false, request.getParameter("id"), null);
                response.sendRedirect(request.getContextPath() + "/user/user_incident?id=" + idInc);
                return;
            }

            //добавить вложение
            if (answer.equals("addAttachment")) {
                List<Typeincident> typs = null;
                int editInca = 0;
                if (request.getParameter("typId") != null) {
                    Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                    typs = gb.getTypesIncidentsForEdit(ti);
                    request.setAttribute("editincident", 1);
                } else {
                    typs = gb.getAllTypesIncident("none");
                    request.setAttribute("editincident", 0);
                }
                request.setAttribute("typs", typs);
                editInca = Integer.parseInt(request.getParameter("editInc"));
                if (editInca == 1) {
                    request.setAttribute("id", request.getParameter("id"));
                }
                request.setAttribute("title", request.getParameter("title"));
                request.setAttribute("texti", request.getParameter("texti"));
                request.setAttribute("editincidenta", editInca);
                request.getRequestDispatcher("/WEB-INF/user/new_incident_file.jsp").forward(request, response);
            }

            getServletContext().setAttribute("editincident", 0);
            List<Typeincident> typs = gb.getAllTypesIncident("none");
            request.setAttribute("typs", typs);
        }

        //закрытые обращения =================================================================================================
        if ("/user/closed_incidents".equals(userPath)) {
            getServletContext().setAttribute("openIncidentsNew", gb.getOpenIncidentsNew(user));
            getServletContext().setAttribute("closedIncidentsNew", gb.getClosedIncidentsNew(user));
            getServletContext().setAttribute("action", userPath);
            filtered = false;
            filterOn(request, user, "none");
        }

        request.getRequestDispatcher("/WEB-INF" + userPath + ".jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String checkAction(HttpServletRequest req) {
        if (req.getParameter("Add") != null) {
            return "Add";
        }
        if (req.getParameter("Cancel") != null) {
            return "Cancel";
        }
        if (req.getParameter("Close") != null) {
            return "Close";
        }
        if (req.getParameter("Edit") != null) {
            return "Edit";
        }
        if (req.getParameter("Done") != null) {
            return "Done";
        }
        if (req.getParameter("Accept") != null) {
            return "Accept";
        }
        if (req.getParameter("NoAccept") != null) {
            return "NoAccept";
        }
        if (req.getParameter("bComm") != null) {
            return "bComm";
        }
        if (req.getParameter("bCommGo") != null) {
            return "bCommGo";
        }
        if (req.getParameter("addAttachment") != null) {
            return "addAttachment";
        }
        if (req.getParameter("bHist") != null) {
            return "bHist";
        }
        if (req.getParameter("bToolsOn") != null) {
            return "bToolsOn";
        }
        if (req.getParameter("bToolsOff") != null) {
            return "bToolsOff";
        }
        if (req.getParameter("dFilter") != null) {
            return "dFilter";
        }
        return "none";
    }

    private void filterOn(HttpServletRequest req, Users user, String attrib) {
        String answer = null;
        answer = checkAction(req);

        getServletContext().setAttribute("openr", 0);
        getServletContext().setAttribute("closer", 1);
        getServletContext().setAttribute("tools", 0);

        if (!filtered) {
            getServletContext().setAttribute("dateb", new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
            getServletContext().setAttribute("datee", new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        }

        //открыть панель
        if (answer.equals("bToolsOn")) {
            getServletContext().setAttribute("tools", 1);
        }

        //фильтр
        if (answer.equals("dFilter") || filtered) {
            if (req.getParameter("dateBegin") != null) {
                dateBeg = req.getParameter("dateBegin");
                dateEnd = req.getParameter("dateEnd");
            }
            if (req.getParameter("group1") != null) {
                String param = req.getParameter("group1");
                if (param != null) {
                    if (param.equals("dClose")) {
                        filterParam = "close";
                    }
                    if (param.equals("dOpen")) {
                        filterParam = "open";
                    }
                }
            }
            if (filterParam.equals("open")) {
                getServletContext().setAttribute("openr", 1);
                getServletContext().setAttribute("closer", 0);
            }
            if (filterParam.equals("close")) {
                getServletContext().setAttribute("openr", 0);
                getServletContext().setAttribute("closer", 1);
            }
            getServletContext().setAttribute("closedIncidents",
                    gb.getClosedIncidentsF(user, attrib, dateBeg, dateEnd, filterParam));
            getServletContext().setAttribute("tools", 1);
            getServletContext().setAttribute("dateb", dateBeg);
            getServletContext().setAttribute("datee", dateEnd);
            filtered = true;
        } else {
            getServletContext().setAttribute("closedIncidents", gb.getClosedIncidents(user, attrib));
        }

        //закрыть панель
        if (answer.equals("bToolsOff")) {
            getServletContext().setAttribute("tools", 0);
        }

    }

}
