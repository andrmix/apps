/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

@WebServlet(name = "incident_controller", urlPatterns = {"/user/user_incident",
    "/user/new_incident", "/user/closed_incidents", "/user/done_incidents",
    "/sort_by_name", "/sort_by_date", "/sort_by_status", "/sort_by_spec",
    "/sort_by_name_closed", "/sort_by_date_closed", "/sort_by_status_closed",
    "/sort_by_spec_closed"})
public class incident_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userPath = request.getServletPath();
        Users user = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", user);

        //сортировка =================================================================================================
        if ("/sort_by_name".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user, "name"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user, "date"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user, "status"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_spec".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user, "spec"));
            request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_name_closed".equals(userPath)) {
            getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user, "name"));
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date_closed".equals(userPath)) {
            getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user, "date"));
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_closed".equals(userPath)) {
            getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user, "status"));
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_spec_closed".equals(userPath)) {
            getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user, "spec"));
            request.getRequestDispatcher("/WEB-INF/user/closed_incidents.jsp").forward(request, response);
        }

        //данные обращения =================================================================================================
        if ("/user/user_incident".equals(userPath)) {
            String answer = null;
            answer = checkAction(request);
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("commento", 1);
            request.setAttribute("ihistory", 0);
            if (incident.getNew1().equals(1) && (incident.getStatus().getId().equals(3) || incident.getStatus().getId().equals(7))) {
                ms.setNotNewIncident(incident);
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

            //редактировать
            if (answer.equals("Edit")) {
                List<Typeincident> typs = ms.getTypesIncidentsForEdit(incident.getTypeIncident());
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

            //комментарии
            if (answer.equals("bComm")) {
                request.setAttribute("commento", 1);
                request.setAttribute("ihistory", 0);
            }

            //комментировать
            if (answer.equals("bCommGo")) {
                request.setAttribute("commento", 1);
                request.setAttribute("ihistory", 0);
                ms.addComment(request.getParameter("textcomm"), user, incident);
            }

            //история
            if (answer.equals("bHist")) {
                request.setAttribute("ihistory", 1);
                request.setAttribute("commento", 0);
            }

            request.setAttribute("incident", incident);
            List<Comments> comments = ms.getComments(incident);
            request.setAttribute("comments", comments);
            List<History> history = ms.getHistory(incident);
            request.setAttribute("allhistory", history);
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
                ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti, true, 0, null);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }

            //редактировать
            if (answer.equals("Edit")) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti, false, Integer.parseInt(request.getParameter("id")), null);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }

            //добавить вложение
            if (answer.equals("addAttachment")) {
                List<Typeincident> typs = null;
                int editInca = 0;
                if (request.getParameter("typId") != null) {
                    Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                    typs = ms.getTypesIncidentsForEdit(ti);
                    request.setAttribute("editincident", 1);
                } else {
                    typs = ms.getAllTypesIncident("none");
                    request.setAttribute("editincident", 0);
                }
                request.setAttribute("typs", typs);
                editInca = Integer.parseInt(request.getParameter("editInc"));
                if (editInca == 1) {
                    request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
                }
                request.setAttribute("title", request.getParameter("title"));
                request.setAttribute("texti", request.getParameter("texti"));
                request.setAttribute("editincidenta", editInca);
                request.getRequestDispatcher("/WEB-INF/user/new_incident_file.jsp").forward(request, response);
            }

            getServletContext().setAttribute("editincident", 0);
            List<Typeincident> typs = ms.getAllTypesIncident("none");
            request.setAttribute("typs", typs);
        }

        //закрытые обращения =================================================================================================
        if ("/user/closed_incidents".equals(userPath)) {
            String answer = null;
            answer = checkAction(request);
            getServletContext().setAttribute("openIncidentsNew", ms.getOpenIncidentsNew(user));
            getServletContext().setAttribute("closedIncidentsNew", ms.getClosedIncidentsNew(user));
            getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user, "none"));
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
        return "none";
    }

}
