package controller;

import entity.Arcincidents;
import entity.Comments;
import entity.History;
import entity.Incidents;
import entity.Typeincident;
import entity.Users;
import filter.Porter;
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

@WebServlet(name = "manager_controller",
        urlPatterns = {"/manager", "/manager/incident_data", "/manager/allocated",
            "/sort_by_name_un", "/sort_by_date_un", "/sort_by_zay_un",
            "/sort_by_name_allo", "/sort_by_date_allo", "/sort_by_status_allo",
            "/sort_by_zay_allo", "/sort_by_spec_allo", "/manager/specialists",
            "/manager/specialist_data", "/manager/new_task", "/manager/on_agreement",
            "/manager/closed", "/manager/manager_incidents", "/manager/manager_done_incidents",
            "/sort_by_name_m_agr", "/sort_by_date_m_agr", "/sort_by_status_m_agr", "/sort_by_zay_m_agr",
            "/sort_by_spec_m_agr", "/sort_by_name_m_closed", "/sort_by_dateo_m_closed",
            "/sort_by_status_m_closed", "/sort_by_zay_m_closed", "/sort_by_spec_m_closed",
            "/sort_by_datec_m_closed", "/sort_by_name_m_act", "/sort_by_date_m_act",
            "/sort_by_status_m_act", "/sort_by_zay_m_act", "/sort_by_name_m_done",
            "/sort_by_dateo_m_done", "/sort_by_zay_m_done", "/sort_by_dated_m_done",
            "/sort_by_status_m_done", "/manager/manager_tools"})
public class manager_controller extends HttpServlet {

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
        Users manager = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", manager);
        getServletContext().setAttribute("unallocatedIncidentsNew", gb.getUnallocatedIncidentsNew());
        getServletContext().setAttribute("agreeIncidentsNew", gb.getAgreeIncidentsNew(manager));
        getServletContext().setAttribute("openIncidentsManagerNew", gb.getSpecialistOpenIncidentsNew(manager));

        String answera = null;
        answera = checkAction(request);
        onSideBar(answera);

        //сортировка нераспределенных обращений =============================================================================================================
        if ("/sort_by_name_un".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", gb.getUnallocatedIncidents("name"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date_un".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", gb.getUnallocatedIncidents("date"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_zay_un".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", gb.getUnallocatedIncidents("zay"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }

        //список нераспределенных обращений =============================================================================================================
        if ("/manager".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", gb.getUnallocatedIncidents("none"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }

        //новое задание =============================================================================================================
        if ("/manager/new_task".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);

            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/manager");
                return;
            }

            //добавить
            if (answer.equals("Add")) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                Users specialist = ms.findUser(request.getParameter("specId"));
                ms.addTask(request.getParameter("title"), request.getParameter("texti"), manager, ti, true, 0, specialist);
                response.sendRedirect(request.getContextPath() + "/manager");
                return;
            }

            //редактировать
            if (answer.equals("Edit")) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                Users specialist = ms.findUser(request.getParameter("specId"));
                ms.addTask(request.getParameter("title"), request.getParameter("texti"), manager, ti, false, Integer.parseInt(request.getParameter("id")), specialist);
                response.sendRedirect(request.getContextPath() + "/manager");
                return;
            }

            request.setAttribute("typs", gb.getAllTypesIncident("none"));
            request.setAttribute("editincident", 0);
            getServletContext().setAttribute("specialists", gb.getSpecialists(null));
            request.getRequestDispatcher("/WEB-INF/manager/new_task.jsp").forward(request, response);
        }

        //сортировка распределенных обращений =============================================================================================================
        if ("/sort_by_name_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", gb.getAllocatedIncidents("name", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", gb.getAllocatedIncidents("date", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", gb.getAllocatedIncidents("status", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_zay_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", gb.getAllocatedIncidents("zay", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_spec_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", gb.getAllocatedIncidents("spec", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }

        //список распределенных обращений =============================================================================================================
        if ("/manager/allocated".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", gb.getAllocatedIncidents("none", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }

        //сортировка списка специалистов =============================================================================================================
        if ("/sort_by_fio_spec".equals(request.getServletPath())) {
            getServletContext().setAttribute("specialistList", gb.getSpecialistsStatistics());
            request.getRequestDispatcher("/WEB-INF/manager/specialists.jsp").forward(request, response);
        }

        //список специалистов =============================================================================================================
        if ("/manager/specialists".equals(request.getServletPath())) {
            getServletContext().setAttribute("specialistList", gb.getSpecialistsStatistics());
            request.getRequestDispatcher("/WEB-INF/manager/specialists.jsp").forward(request, response);
        }

        //данные специалиста =============================================================================================================
        if ("/manager/specialist_data".equals(request.getServletPath())) {
            Users specialist = ms.findUser(request.getParameter("id"));
            List stats = gb.getOneSpecialistsStatistics(specialist.getLogin());
            request.setAttribute("statList", stats);
            List statsYear1 = gb.getYearStatistic("2016", specialist.getLogin(), 1);
            List statsYear2 = gb.getYearStatistic("2016", specialist.getLogin(), 7);
            request.setAttribute("statYearList1", statsYear1);
            request.setAttribute("statYearList2", statsYear2);
            request.getRequestDispatcher("/WEB-INF/manager/specialist_data.jsp").forward(request, response);
        }

        //сортировка на согласовании =============================================================================================================
        if ("/sort_by_name_m_agr".equals(request.getServletPath())) {
            getServletContext().setAttribute("agreeIncidents", gb.getAgreeIncidents(manager, "name"));
            request.getRequestDispatcher("/WEB-INF/manager/on_agreement.jsp").forward(request, response);
        }
        if ("/sort_by_date_m_agr".equals(request.getServletPath())) {
            getServletContext().setAttribute("agreeIncidents", gb.getAgreeIncidents(manager, "date"));
            request.getRequestDispatcher("/WEB-INF/manager/on_agreement.jsp").forward(request, response);
        }
        if ("/sort_by_status_m_agr".equals(request.getServletPath())) {
            getServletContext().setAttribute("agreeIncidents", gb.getAgreeIncidents(manager, "status"));
            request.getRequestDispatcher("/WEB-INF/manager/on_agreement.jsp").forward(request, response);
        }
        if ("/sort_by_zay_m_agr".equals(request.getServletPath())) {
            getServletContext().setAttribute("agreeIncidents", gb.getAgreeIncidents(manager, "zay"));
            request.getRequestDispatcher("/WEB-INF/manager/on_agreement.jsp").forward(request, response);
        }
        if ("/sort_by_spec_m_agr".equals(request.getServletPath())) {
            getServletContext().setAttribute("agreeIncidents", gb.getAgreeIncidents(manager, "spec"));
            request.getRequestDispatcher("/WEB-INF/manager/on_agreement.jsp").forward(request, response);
        }

        //на согласовании =============================================================================================================
        if ("/manager/on_agreement".equals(request.getServletPath())) {
            getServletContext().setAttribute("agreeIncidents", gb.getAgreeIncidents(manager, "none"));
            request.getRequestDispatcher("/WEB-INF/manager/on_agreement.jsp").forward(request, response);
        }

        //данные обращения =============================================================================================================
        if ("/manager/incident_data".equals(request.getServletPath())) {
            getServletContext().setAttribute("commento", 0);
            getServletContext().setAttribute("ihistory", 0);
            getServletContext().setAttribute("commenta", 0);
            getServletContext().setAttribute("appoint", 0);
            getServletContext().setAttribute("task", 0);

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
                    List<Comments> comments = gb.getComments(null, arcincident);
                    request.setAttribute("comments", comments);
                }

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("commento", 0);
                    List<History> history = gb.getHistory(null, arcincident);
                    request.setAttribute("allhistory", history);
                }

                //удалить
                if (answer.equals("Delete")) {
                    ms.deleteIncidentFull(null, arcincident);
                    response.sendRedirect(request.getContextPath() + "/manager");
                    return;
                }

                //удалить
                if (answer.equals("Send")) {
                    //ms.sendMail();
                }
            } else {
                if (incident.getNew1().equals(1)) {
                    if (incident.getStatus().getId().equals(1)) {
                        ms.setNotNewIncident(incident, null);
                    }
                    if (incident.getStatus().getId().equals(5)) {
                        ms.setNotNewIncident(incident, null);
                    }
                    if (incident.getZayavitel().equals(incident.getManager())
                            && incident.getStatus().getId().equals(4)) {
                        ms.setNotNewIncident(incident, null);
                    }
                    if (incident.getStatus().getId().equals(2)
                            && incident.getSpecialist().equals(incident.getManager())) {
                        ms.setNotNewIncident(incident, null);
                    }
                }

                if (ms.isTask(manager, incident)) {
                    getServletContext().setAttribute("task", 1);
                }

                request.setAttribute("incident", incident);

                //готово - назначение специалиста
                if (answer.equals("Done")) {
                    Users user = ms.findUser(request.getParameter("specId"));
                    ms.addSpecialist(incident, user, manager);
                    //ms.sendMail();
                    response.sendRedirect(request.getContextPath() + "/manager");
                    return;
                }

                //назначить
                if (answer.equals("Appoint")) {
                    getServletContext().setAttribute("specialists", gb.getSpecialists(null));
                    getServletContext().setAttribute("appoint", 1);
                }

                //отклонить
                if (answer.equals("Close")) {
                    getServletContext().setAttribute("commenta", 1);
                }

                //согласовать
                if (answer.equals("Agree")) {
                    ms.agreeIncident(incident);
                    response.sendRedirect(request.getContextPath() + "/manager/on_agreement");
                    return;
                }

                //готово - отклонение/отмена
                if (answer.equals("pDone")) {
                    if (ms.isTask(manager, incident)) {
                        ms.cancelIncident(incident, request.getParameter("textc"), request.getParameter("status"), false, manager);
                    } else {
                        ms.cancelIncident(incident, request.getParameter("textc"), request.getParameter("status"), true, manager);
                    }
                    response.sendRedirect(request.getContextPath() + "/manager");
                    return;
                }

                //комментировать
                if (answer.equals("bCommGo")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                    ms.addComment(request.getParameter("textcomm"), manager, incident);
                    List<Comments> comments = gb.getComments(incident, null);
                    request.setAttribute("comments", comments);
                }

                //в работу
                if (answer.equals("InWork")) {
                    ms.inWork(incident);
                    response.sendRedirect(request.getContextPath() + "/manager");
                    return;
                }

                //выполнить
                if (answer.equals("Doit")) {
                    request.setAttribute("done", 1);
                }

                //Выполнить - Готово
                if (answer.equals("gDone")) {
                    ms.doneIncident(incident, request.getParameter("decision"));
                    response.sendRedirect(request.getContextPath() + "/manager/manager_done_incidents");
                    return;
                }

                //комментарии
                if (answer.equals("bComm")) {
                    request.setAttribute("commento", 1);
                    request.setAttribute("ihistory", 0);
                    List<Comments> comments = gb.getComments(incident, null);
                    request.setAttribute("comments", comments);
                }

                //история
                if (answer.equals("bHist")) {
                    request.setAttribute("ihistory", 1);
                    request.setAttribute("commento", 0);
                    List<History> history = gb.getHistory(incident, null);
                    request.setAttribute("allhistory", history);
                }

                //удалить
                if (answer.equals("Send")) {
                    //ms.sendMail();
                }

            }

            request.getRequestDispatcher("/WEB-INF/manager/incident_data.jsp").forward(request, response);
        }

        //сортировка закрытых обращений =============================================================================================================
        if ("/sort_by_name_m_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, "name");
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }
        if ("/sort_by_dateo_m_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, "dateo");
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }
        if ("/sort_by_status_m_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, "status");
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }
        if ("/sort_by_zay_m_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, "zay");
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }
        if ("/sort_by_spec_m_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, "spec");
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }
        if ("/sort_by_datec_m_closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, "datec");
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }

        //список закрытых обращений =============================================================================================================
        if ("/manager/closed".equals(request.getServletPath())) {
            filtered = false;
            getServletContext().setAttribute("action", request.getServletPath());
            filterOn(request, "none");
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }

        //сортировка обращений менеджера =============================================================================================================
        if ("/sort_by_name_m_act".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidentsManager", gb.getSpecialistOpenIncidents(manager, "name"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date_m_act".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidentsManager", gb.getSpecialistOpenIncidents(manager, "date"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_m_act".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidentsManager", gb.getSpecialistOpenIncidents(manager, "status"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_zay_m_act".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidentsManager", gb.getSpecialistOpenIncidents(manager, "zay"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_incidents.jsp").forward(request, response);
        }

        //список обращений менеджера =============================================================================================================
        if ("/manager/manager_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidentsManager", gb.getSpecialistOpenIncidents(manager, "none"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_incidents.jsp").forward(request, response);
        }

        //сортировка выполненных обращений менеджера =============================================================================================================
        if ("/sort_by_name_m_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidentsManager", gb.getSpecialistDoneIncidents(manager, "name"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_done_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_dateo_m_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidentsManager", gb.getSpecialistDoneIncidents(manager, "dateo"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_done_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_zay_m_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidentsManager", gb.getSpecialistDoneIncidents(manager, "zay"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_done_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_dated_m_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidentsManager", gb.getSpecialistDoneIncidents(manager, "dated"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_done_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_m_done".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidentsManager", gb.getSpecialistDoneIncidents(manager, "status"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_done_incidents.jsp").forward(request, response);
        }

        //список обращений менеджера =============================================================================================================
        if ("/manager/manager_done_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidentsManager", gb.getSpecialistDoneIncidents(manager, "none"));
            request.getRequestDispatcher("/WEB-INF/manager/manager_done_incidents.jsp").forward(request, response);
        }

        //настройки =============================================================================================================
        if ("/manager/manager_tools".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            getServletContext().setAttribute("inp", 0);

            if (answer.equals("Back")) {
                ms.comeBack(manager);
            }
            
            if (answer.equals("Done")) {
                Users user = ms.findUser(request.getParameter("specId")); 
                if (user.getLogin().equals("auto")){
                    ms.autoOn(manager);
                } else {
                    ms.doManager(user, manager);
                }
            }

            getServletContext().setAttribute("specialists", gb.getSpecialists(manager));
            request.getRequestDispatcher("/WEB-INF/manager/manager_tools.jsp").forward(request, response);
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
        if (req.getParameter("Appoint") != null) {
            return "Appoint";
        }
        if (req.getParameter("Cancel") != null) {
            return "Cancel";
        }
        if (req.getParameter("Close") != null) {
            return "Close";
        }
        if (req.getParameter("Done") != null) {
            return "Done";
        }
        if (req.getParameter("pDone") != null) {
            return "pDone";
        }
        if (req.getParameter("Edit") != null) {
            return "Edit";
        }
        if (req.getParameter("Add") != null) {
            return "Add";
        }
        if (req.getParameter("Agree") != null) {
            return "Agree";
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
        if (req.getParameter("rolemoder") != null) {
            return "rolemoder";
        }
        if (req.getParameter("rolespec") != null) {
            return "rolespec";
        }
        if (req.getParameter("InWork") != null) {
            return "InWork";
        }
        if (req.getParameter("Doit") != null) {
            return "Doit";
        }
        if (req.getParameter("gDone") != null) {
            return "gDone";
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
        if (req.getParameter("Delete") != null) {
            return "Delete";
        }
        if (req.getParameter("Send") != null) {
            return "Send";
        }
        if (req.getParameter("Back") != null) {
            return "Back";
        }
        return "none";
    }

    private void onSideBar(String role) {
        //руководитель
        if (role.equals("rolemoder")) {
            getServletContext().setAttribute("ismoder", 1);
            getServletContext().setAttribute("isspec", 0);
        }

        //специалист
        if (role.equals("rolespec")) {
            getServletContext().setAttribute("ismoder", 0);
            getServletContext().setAttribute("isspec", 1);
        }
    }

    private void filterOn(HttpServletRequest req, String attrib) {
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
            getServletContext().setAttribute("closedIncidentsManager",
                    gb.getClosedIncidentsMF(attrib, dateBeg, dateEnd, filterParam));
            getServletContext().setAttribute("tools", 1);
            getServletContext().setAttribute("dateb", dateBeg);
            getServletContext().setAttribute("datee", dateEnd);
            filtered = true;
        } else {
            getServletContext().setAttribute("closedIncidentsManager", gb.getClosedIncidentsManager(attrib));
        }

        //закрыть панель
        if (answer.equals("bToolsOff")) {
            getServletContext().setAttribute("tools", 0);
        }

    }
}
