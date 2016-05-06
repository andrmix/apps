/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Arccomments;
import entity.Arcdocs;
import entity.Archistory;
import entity.Arcincidents;
import entity.Comments;
import entity.Docs;
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
            "/specialist/sort_by_datec_closed", "/specialist/spec_act_zamena",
            "/specialist/spec_act_done", "/specialist/sort_by_id_closed",
            "/specialist/sort_by_id_done", "/specialist/sort_by_id_open"})
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

        if ("/specialist/sort_by_id_open".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidents", gb.getSpecialistOpenIncidents(specialist, "id"));
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

        if ("/specialist/sort_by_id_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", gb.getSpecialistDoneIncidents(specialist, "id"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
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

        if ("/specialist/sort_by_id_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, specialist, "id");
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
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
            request.setAttribute("icomm", 1);
            request.setAttribute("ihistory", 0);
            request.setAttribute("iresh", 0);
            String answer = null;
            answer = checkAction(request);

            String idIncident = null;
            idIncident = request.getParameter("id");

            Incidents incident = ms.findIncident(idIncident);
            if (incident == null) {
                Arcincidents arcincident = ms.findArcIncident(idIncident);
                request.setAttribute("incident", arcincident);
                List<Arccomments> arccomments = gb.getArccomments(arcincident);
                request.setAttribute("comments", arccomments);
                List<Arcdocs> arcReqs = gb.getArcReqs(arcincident);
                request.setAttribute("reqs", arcReqs);
                List<Arcdocs> arcActs = gb.getArcActDone(arcincident);
                request.setAttribute("acts", arcActs);

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("iresh", 0);
                    request.setAttribute("icomm", 0);
                    List<Archistory> archistory = gb.getArchistory(arcincident);
                    request.setAttribute("allhistory", archistory);
                }

                //comments
                if (answer.equals("bComms")) {
                    request.setAttribute("icomm", 1);
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("iresh", 0);
                }

                if (answer.equals("zOpen")) {
                    Arcdocs zArcReq = ms.findArcDoc(Integer.parseInt(request.getParameter("zId")));
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_act_zamena?id="
                            + zArcReq.getId() + "&incId=" + arcincident.getId() + "&incDate=" + arcincident.getDateIncident());
                    return;
                }

                if (answer.equals("aOpen")) {
                    Arcdocs aArcAct = ms.findArcDoc(Integer.parseInt(request.getParameter("aId")));
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_act_done?id="
                            + aArcAct.getId() + "&incId=" + arcincident.getId() + "&incDate=" + arcincident.getDateIncident());
                    return;
                }
            } else {
                request.setAttribute("incident", incident);
                List<Comments> comments = gb.getComments(incident);
                request.setAttribute("comments", comments);
                List<Docs> reqs = gb.getReqs(incident);
                request.setAttribute("reqs", reqs);

                if (incident.getNew1().equals(1) && (incident.getStatus().getId().equals(2) || incident.getStatus().getId().equals(3))) {
                    ms.setNotNewIncident(incident, null);
                }

                //В работу
                if (answer.equals("InWork")) {
                    ms.inWork(incident);
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_incident_data?id=" + incident.getId());
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
                    if (request.getParameter("kb") != null) {
                        ms.doneIncident(incident, request.getParameter("decision"), true);
                    } else {
                        ms.doneIncident(incident, request.getParameter("decision"), false);
                    }
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_incident_data?id=" + incident.getId());
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
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("iresh", 0);
                    request.setAttribute("icomm", 1);
                    ms.addComment(request.getParameter("textcomm"), specialist, incident);
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_incident_data?id=" + incident.getId());
                    return;
                }

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("iresh", 0);
                    request.setAttribute("icomm", 0);
                    List<History> history = gb.getHistory(incident);
                    request.setAttribute("allhistory", history);
                }

                //похожие обращения
                if (answer.equals("bResh")) {
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("iresh", 1);
                    request.setAttribute("icomm", 0);
                    List arcincidents = gb.getSimilarIncidents(incident);
                    request.setAttribute("rIncidents", arcincidents);
                }

                //comments
                if (answer.equals("bComm")) {
                    request.setAttribute("ihistory", 0);
                    request.setAttribute("iresh", 0);
                    request.setAttribute("icomm", 1);
                }

                //замена оборудования
                if (answer.equals("Zamena")) {
                    request.setAttribute("zamenaP", 1);
                    List<Users> komises1 = gb.getAllUsers("none");
                    List<Users> komises2 = gb.getAllUsers("none");
                    request.setAttribute("komises1", komises1);
                    request.setAttribute("komises2", komises2);
                    request.setAttribute("zEd", 0);
                }

                //замена оборудования - готово
                if (answer.equals("zDone")) {
                    Users komis1 = ms.findUser(request.getParameter("komisId1"));
                    Users komis2 = ms.findUser(request.getParameter("komisId2"));
                    int idReq = ms.addReq(specialist, request.getParameter("prich"), incident, komis1, komis2, request.getParameter("hw_on"), request.getParameter("hw_off"), false, null);
                    List<Docs> reqas = gb.getReqs(incident);
                    Docs eReq = null;
                    for (Docs requ : reqas) {
                        eReq = requ;
                    }
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_act_zamena?id="
                            + eReq.getId() + "&incId=" + incident.getId() + "&incDate=" + incident.getDateIncident());
                    return;
                }

                if (answer.equals("zEdit")) {
                    request.setAttribute("zamenaP", 1);
                    Docs zReq = ms.findDoc(Integer.parseInt(request.getParameter("zId")));
                    List<Users> komises1 = gb.getUsersForEdit(zReq.getKomis1());
                    List<Users> komises2 = gb.getUsersForEdit(zReq.getKomis2());
                    request.setAttribute("komises1", komises1);
                    request.setAttribute("komises2", komises2);
                    request.setAttribute("req", zReq);
                    request.setAttribute("zEd", 1);
                }

                if (answer.equals("zEditDone")) {
                    Users komis1 = ms.findUser(request.getParameter("komisId1"));
                    Users komis2 = ms.findUser(request.getParameter("komisId2"));
                    Docs zReq = ms.findDoc(Integer.parseInt(request.getParameter("rId")));
                    int idReq = ms.addReq(specialist, request.getParameter("prich"), incident, komis1, komis2, request.getParameter("hw_on"), request.getParameter("hw_off"), true, zReq);
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_act_zamena?id="
                            + idReq + "&incId=" + incident.getId() + "&incDate=" + incident.getDateIncident());
                    return;
                }

                if (answer.equals("zOpen")) {
                    Docs zReq = ms.findDoc(Integer.parseInt(request.getParameter("zId")));
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_act_zamena?id="
                            + zReq.getId() + "&incId=" + incident.getId() + "&incDate=" + incident.getDateIncident());
                    return;
                }

                if (answer.equals("zDel")) {
                    Docs zReq = ms.findDoc(Integer.parseInt(request.getParameter("zId")));
                    ms.deleteDoc(zReq);
                    response.sendRedirect(request.getContextPath() + "/specialist/spec_incident_data?id=" + incident.getId());
                    return;
                }
            }

            request.getRequestDispatcher("/WEB-INF/specialist/spec_incident_data.jsp").forward(request, response);
        }

        //заявка на замену оборудования ==================================================================================================
        if ("/specialist/spec_act_zamena".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);

            int idReq = 0;
            idReq = Integer.parseInt(request.getParameter("id"));
            Docs req = ms.findDoc(idReq);
            if (req == null) {
                Arcdocs arcreq = ms.findArcDoc(idReq);
                getServletContext().setAttribute("req", arcreq);
            } else {
                getServletContext().setAttribute("req", req);
            }

            getServletContext().setAttribute("inc_id", request.getParameter("incId"));
            getServletContext().setAttribute("inc_date", request.getParameter("incDate"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_act_zamena.jsp").forward(request, response);
        }

        //акт выполненных работ ==================================================================================================
        if ("/specialist/spec_act_done".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);

            int idAct = 0;
            idAct = Integer.parseInt(request.getParameter("id"));

            Docs act = ms.findDoc(idAct);
            if (act == null) {
                Arcdocs arcact = ms.findArcDoc(idAct);
                getServletContext().setAttribute("act", arcact);
            } else {
                getServletContext().setAttribute("act", act);
            }

            getServletContext().setAttribute("inc_id", request.getParameter("incId"));
            getServletContext().setAttribute("inc_date", request.getParameter("incDate"));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_act_done.jsp").forward(request, response);
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
            
            List statsInc = gb.getSpecIncidentsStatistics(specialist);
            request.setAttribute("typiscList", statsInc);
            
            String answer = null;
            answer = checkAction(request);

            if (answer.equals("bcharts")) {
                String yearc = request.getParameter("yearc");
                String monc = request.getParameter("monc");
                getServletContext().setAttribute("typisList", gb.getSpecIncidentsStatisticsMonth(yearc, monc, specialist));
                request.getRequestDispatcher("/WEB-INF/specialist/statistic.jsp").forward(request, response);
            }
            
            List statsIncMon = gb.getSpecIncidentsStatisticsMonth("2016", "1", specialist);
            request.setAttribute("typisList", statsIncMon);
            
            
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
        if (req.getParameter("zEdit") != null) {
            return "zEdit";
        }
        if (req.getParameter("zEditDone") != null) {
            return "zEditDone";
        }
        if (req.getParameter("zOpen") != null) {
            return "zOpen";
        }
        if (req.getParameter("aOpen") != null) {
            return "aOpen";
        }
        if (req.getParameter("zDel") != null) {
            return "zDel";
        }
        if (req.getParameter("bcharts") != null) {
            return "bcharts";
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
