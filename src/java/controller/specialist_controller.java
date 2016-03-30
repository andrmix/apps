/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Arcincidents;
import entity.Comments;
import entity.History;
import entity.Incidents;
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
import javax.servlet.http.HttpSession;
import session.GetterBeanLocal;
import session.ManagementSystemLocal;

@WebServlet(name = "specialist_controller",
        urlPatterns = {"/specialist", "/specialist/spec_incident_data",
            "/specialist/spec_done_incidents", "/specialist/spec_done_incident_data",
            "/specialist/spec_closed_incidents", "/specialist/statistic",
            "/specialist/sort_by_name_open", "/specialist/sort_by_date_open",
            "/specialist/sort_by_status_open", "/specialist/sort_by_zay_open",
            "/specialist/sort_by_name_done", "/specialist/sort_by_dateo_done",
            "/specialist/sort_by_zay_done", "/specialist/sort_by_dated_done",
            "/specialist/sort_by_name_closed", "/specialist/sort_by_dateo_closed",
            "/specialist/sort_by_status_closed", "/specialist/sort_by_zay_closed",
            "/specialist/sort_by_datec_closed"})
public class specialist_controller extends HttpServlet {

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
        Users specialist = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", specialist);
        getServletContext().setAttribute("openIncidentsNew", gb.getSpecialistOpenIncidentsNew(specialist));
        getServletContext().setAttribute("blo", 0);

        //обращения =============================================================================================================
        if ("/specialist".equals(request.getServletPath())) {
            if (ms.isBlockedUser(specialist)) {
                getServletContext().setAttribute("blo", 1);
                request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
            }
            getServletContext().setAttribute("openIncidents", gb.getSpecialistOpenIncidents(specialist, "none"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
        }

        if ("/specialist/sort_by_name_open".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidents", gb.getSpecialistOpenIncidents(specialist, "name"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_date_open".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidents", gb.getSpecialistOpenIncidents(specialist, "date"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_status_open".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidents", gb.getSpecialistOpenIncidents(specialist, "status"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_zay_open".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidents", gb.getSpecialistOpenIncidents(specialist, "zay"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
        }

        if ("/specialist/sort_by_name_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", gb.getSpecialistDoneIncidents(specialist, "name"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_dateo_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", gb.getSpecialistDoneIncidents(specialist, "dateo"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_zay_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", gb.getSpecialistDoneIncidents(specialist, "zay"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_dated_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", gb.getSpecialistDoneIncidents(specialist, "dated"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_status_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", gb.getSpecialistDoneIncidents(specialist, "status"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }

        if ("/specialist/sort_by_name_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, specialist, "name");
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_dateo_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, specialist, "dateo");
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_status_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, specialist, "status");
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_zay_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, specialist, "zay");
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
        }
        if ("/specialist/sort_by_datec_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, specialist, "datec");
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
        }

        //данные обращения =============================================================================================================        
        if ("/specialist/spec_incident_data".equals(request.getServletPath())) {
            request.setAttribute("done", 0);
            request.setAttribute("otmena", 0);
            request.setAttribute("zamenaP", 0);
            request.setAttribute("commento", 0);
            request.setAttribute("ihistory", 0);
            request.setAttribute("iresh", 0);
            String answer = null;
            answer = checkAction(request);

            int idIncident = 0;
            idIncident = Integer.parseInt(request.getParameter("id"));

            Incidents incident = ms.findIncident(idIncident);
            if (incident == null) {
                Arcincidents arcincident = ms.findArcIncident(idIncident);
                request.setAttribute("incident", arcincident);

                //комментарии
                if (answer.equals("bComm")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("iresh", 0);
                    List<Comments> comments = gb.getComments(null, arcincident);
                    request.setAttribute("comments", comments);
                }

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("commento", 0);
                    request.setAttribute("iresh", 0);
                    List<History> history = gb.getHistory(null, arcincident);
                    request.setAttribute("allhistory", history);
                }
            } else {
                request.setAttribute("incident", incident);
                if (incident.getNew1().equals(1) && (incident.getStatus().getId().equals(2) || incident.getStatus().getId().equals(3))) {
                    ms.setNotNewIncident(incident, null);
                }

                //В работу
                if (answer.equals("InWork")) {
                    ms.inWork(incident);
                    response.sendRedirect(request.getContextPath() + "/specialist");
                    return;
                }

                //Отменить
                if (answer.equals("Close")) {
                    request.setAttribute("otmena", 1);
                }

                //Выполнить
                if (answer.equals("Doit")) {
                    request.setAttribute("done", 1);
                }

                //Выполнить - Готово
                if (answer.equals("Done")) {
                    ms.doneIncident(incident, request.getParameter("decision"));
                    response.sendRedirect(request.getContextPath() + "/specialist");
                    return;
                }

                //Отменить - Готово
                if (answer.equals("pDone")) {
                    ms.cancelIncident(incident, request.getParameter("textc"), request.getParameter("status"), true, null);
                    response.sendRedirect(request.getContextPath() + "/specialist");
                    return;
                }

                //комментировать
                if (answer.equals("bCommGo")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("iresh", 0);
                    ms.addComment(request.getParameter("textcomm"), specialist, incident);
                    List<Comments> comments = gb.getComments(incident, null);
                    request.setAttribute("comments", comments);
                }

                //комментарии
                if (answer.equals("bComm")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("iresh", 0);
                    List<Comments> comments = gb.getComments(incident, null);
                    request.setAttribute("comments", comments);
                }

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("commento", 0);
                    request.setAttribute("iresh", 0);
                    List<History> history = gb.getHistory(incident, null);
                    request.setAttribute("allhistory", history);
                }

                //похожие обращения
                if (answer.equals("bResh")) {
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("commento", 0);
                    request.setAttribute("iresh", 1);
                    List arcincidents = gb.getSimilarIncidents(incident);
                    request.setAttribute("rIncidents", arcincidents);
                }
                
                //замена оборудования
                if (answer.equals("Zamena")) {
                    request.setAttribute("zamenaP", 1);
                }
                
                //замена оборудования - готово
                if (answer.equals("zDone")) {
                    ms.addReq(specialist, request.getParameter("textz"), incident);
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_incident_data?id=" + incident.getId());
                    return;
                }
            }

            request.getRequestDispatcher("/WEB-INF/specialist/spec_incident_data.jsp").forward(request, response);
        }

        //выполненные обращения ==================================================================================================
        if ("/specialist/spec_done_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", gb.getSpecialistDoneIncidents(specialist, "none"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }

        //закрытые обращения ==================================================================================================
        if ("/specialist/spec_closed_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filtered = false;
            filterOn(request, specialist, "none");
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
        }

        //статистика ==================================================================================================
        if ("/specialist/statistic".equals(request.getServletPath())) {
            List stats = gb.getOneSpecialistsStatistics(specialist.getLogin());
            request.setAttribute("statList", stats);
            List statsYear1 = gb.getYearStatistic("2016", specialist.getLogin(), 1);
            List statsYear2 = gb.getYearStatistic("2016", specialist.getLogin(), 7);
            request.setAttribute("statYearList1", statsYear1);
            request.setAttribute("statYearList2", statsYear2);
            request.getRequestDispatcher("/WEB-INF/specialist/statistic.jsp").forward(request, response);
        }
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
        if (req.getParameter("InWork") != null) {
            return "InWork";
        }
        if (req.getParameter("Close") != null) {
            return "Close";
        }
        if (req.getParameter("Doit") != null) {
            return "Doit";
        }
        if (req.getParameter("Done") != null) {
            return "Done";
        }
        if (req.getParameter("pDone") != null) {
            return "pDone";
        }
        if (req.getParameter("bComm") != null) {
            return "bComm";
        }
        if (req.getParameter("bCommGo") != null) {
            return "bCommGo";
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
        if (req.getParameter("bResh") != null) {
            return "bResh";
        }
        if (req.getParameter("Zamena") != null) {
            return "Zamena";
        }
        if (req.getParameter("zDone") != null) {
            return "zDone";
        }
        return "none";
    }

    private void filterOn(HttpServletRequest req, Users specialist, String attrib) {
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
                    gb.getSpecialistClosedIncidentsF(specialist, attrib, dateBeg, dateEnd, filterParam));
            getServletContext().setAttribute("tools", 1);
            getServletContext().setAttribute("dateb", dateBeg);
            getServletContext().setAttribute("datee", dateEnd);
            filtered = true;
        } else {
            getServletContext().setAttribute("closedIncidents", gb.getSpecialistClosedIncidents(specialist, attrib));
        }

        //закрыть панель
        if (answer.equals("bToolsOff")) {
            getServletContext().setAttribute("tools", 0);
        }
    }

}
