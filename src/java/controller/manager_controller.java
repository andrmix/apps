package controller;

import entity.Comments;
import entity.History;
import entity.Incidents;
import entity.Typeincident;
import entity.Users;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ManagementSystemLocal;

/**
 *
 * @author admin
 */
@WebServlet(name = "manager_controller",
        urlPatterns = {"/manager", "/manager/incident_data", "/manager/allocated",
            "/sort_by_name_un", "/sort_by_date_un", "/sort_by_status_un",
            "/sort_by_zay_un", "/sort_by_name_allo", "/sort_by_date_allo",
            "/sort_by_status_allo", "/sort_by_zay_allo", "/sort_by_spec_allo",
            "/manager/specialists", "/manager/specialist_data", "/manager/new_task",
            "/manager/on_agreement", "/manager/closed", "/manager/manager_incidents",
            "/manager/manager_done_incidents"})
public class manager_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Users manager = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", manager);
        getServletContext().setAttribute("unallocatedIncidentsNew", ms.getUnallocatedIncidentsNew());
        getServletContext().setAttribute("agreeIncidentsNew", ms.getAgreeIncidentsNew(manager));
        getServletContext().setAttribute("openIncidentsManagerNew", ms.getSpecialistOpenIncidentsNew(manager));

        String answera = null;
        answera = checkAction(request);
        onSideBar(answera);

        //сортировка нераспределенных обращений =============================================================================================================
        if ("/sort_by_name_un".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", ms.getUnallocatedIncidents("name"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date_un".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", ms.getUnallocatedIncidents("date"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_un".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", ms.getUnallocatedIncidents("status"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_zay_un".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", ms.getUnallocatedIncidents("zay"));
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }

        //список нераспределенных обращений =============================================================================================================
        if ("/manager".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", ms.getUnallocatedIncidents("none"));
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

            request.setAttribute("typs", ms.getAllTypesIncident("none"));
            request.setAttribute("editincident", 0);
            getServletContext().setAttribute("specialists", ms.getSpecialists("none"));
            request.getRequestDispatcher("/WEB-INF/manager/new_task.jsp").forward(request, response);
        }

        //сортировка распределенных обращений =============================================================================================================
        if ("/sort_by_name_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("name", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("date", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("status", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_zay_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("zay", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_spec_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("spec", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }

        //список распределенных обращений =============================================================================================================
        if ("/manager/allocated".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("none", manager));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }

        //сортировка списка специалистов =============================================================================================================
        if ("/sort_by_fio_spec".equals(request.getServletPath())) {
            getServletContext().setAttribute("specialistList", ms.getSpecialistsStatistics());
            request.getRequestDispatcher("/WEB-INF/manager/specialists.jsp").forward(request, response);
        }

        //список специалистов =============================================================================================================
        if ("/manager/specialists".equals(request.getServletPath())) {
            getServletContext().setAttribute("specialistList", ms.getSpecialistsStatistics());
            request.getRequestDispatcher("/WEB-INF/manager/specialists.jsp").forward(request, response);
        }

        //данные специалиста =============================================================================================================
        if ("/manager/specialist_data".equals(request.getServletPath())) {
            Users specialist = ms.findUser(request.getParameter("id"));
            List stats = ms.getOneSpecialistsStatistics(specialist.getLogin());
            request.setAttribute("statList", stats);
            List statsYear1 = ms.getYearStatistic("2016", specialist.getLogin(), 1);
            List statsYear2 = ms.getYearStatistic("2016", specialist.getLogin(), 7);
            request.setAttribute("statYearList1", statsYear1);
            request.setAttribute("statYearList2", statsYear2);
            request.getRequestDispatcher("/WEB-INF/manager/specialist_data.jsp").forward(request, response);
        }

        //на согласовании =============================================================================================================
        if ("/manager/on_agreement".equals(request.getServletPath())) {
            getServletContext().setAttribute("agreeIncidents", ms.getAgreeIncidents(manager));
            request.getRequestDispatcher("/WEB-INF/manager/on_agreement.jsp").forward(request, response);
        }

        //данные обращения =============================================================================================================
        if ("/manager/incident_data".equals(request.getServletPath())) {
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("commento", 1);
            request.setAttribute("ihistory", 0);
            if (incident.getNew1().equals(1)) {
                if (incident.getStatus().getId().equals(1)) {
                    ms.setNotNewIncident(incident);
                }
                if (incident.getZayavitel().equals(incident.getManager())
                        && incident.getStatus().getId().equals(3)) {
                    ms.setNotNewIncident(incident);
                }
                if (incident.getStatus().getId().equals(8)) {
                    ms.setNotNewIncident(incident);
                }
                if (incident.getStatus().getId().equals(6)
                        && incident.getSpecialist().equals(incident.getManager())) {
                    ms.setNotNewIncident(incident);
                }
            }
            request.setAttribute("incident", incident);
            getServletContext().setAttribute("commenta", 0);
            String answer = null;
            answer = checkAction(request);
            getServletContext().setAttribute("appoint", 0);
            getServletContext().setAttribute("task", 0);
            if (ms.isTask(manager, incident)) {
                getServletContext().setAttribute("task", 1);
            }

            //назначить
            if (answer.equals("Appoint")) {
                getServletContext().setAttribute("specialists", ms.getSpecialists("none"));
                getServletContext().setAttribute("appoint", 1);
            }

            //готово - назначение специалиста
            if (answer.equals("Done")) {
                Users user = ms.findUser(request.getParameter("specId"));
                ms.addSpecialist(incident, user, manager);
                response.sendRedirect(request.getContextPath() + "/manager");
                return;
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

            //комментарии
            if (answer.equals("bComm")) {
                request.setAttribute("commento", 1);
                request.setAttribute("ihistory", 0);
            }

            //комментировать
            if (answer.equals("bCommGo")) {
                request.setAttribute("commento", 1);
                request.setAttribute("ihistory", 0);
                ms.addComment(request.getParameter("textcomm"), manager, incident);
            }
            
            //история
            if (answer.equals("bHist")) {
                request.setAttribute("ihistory", 1);
                request.setAttribute("commento", 0);
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

            List<Comments> comments = ms.getComments(incident);
            request.setAttribute("comments", comments);
            List<History> history = ms.getHistory(incident);
            request.setAttribute("allhistory", history);
            request.getRequestDispatcher("/WEB-INF/manager/incident_data.jsp").forward(request, response);
        }

        //список закрытых обращений =============================================================================================================
        if ("/manager/closed".equals(request.getServletPath())) {
            getServletContext().setAttribute("closedIncidentsManager", ms.getClosedIncidentsManager("none"));
            request.getRequestDispatcher("/WEB-INF/manager/closed.jsp").forward(request, response);
        }

        //список обращений менеджера =============================================================================================================
        if ("/manager/manager_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidentsManager", ms.getSpecialistOpenIncidents(manager));
            request.getRequestDispatcher("/WEB-INF/manager/manager_incidents.jsp").forward(request, response);
        }

        //список обращений менеджера =============================================================================================================
        if ("/manager/manager_done_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidentsManager", ms.getSpecialistDoneIncidents(manager));
            request.getRequestDispatcher("/WEB-INF/manager/manager_done_incidents.jsp").forward(request, response);
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
}
