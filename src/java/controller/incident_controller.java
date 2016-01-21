/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Comments;
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

        if ("/user/user_incident".equals(userPath)) {
            int answer = 0;
            answer = checkAction(request);
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            if (answer == 2) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer == 3) {
                request.setAttribute("commenta", 1);
            }
            if (answer == 4) {
                List<Typeincident> typs = ms.getTypesIncidentsForEdit(incident.getTypeIncident());
                request.setAttribute("incident", incident);
                request.setAttribute("typs", typs);
                request.setAttribute("editincident", 1);
                request.getRequestDispatcher("/WEB-INF/user/new_incident.jsp").forward(request, response);
            }
            if (answer == 5) {
                ms.cancelIncident(incident, request.getParameter("textc"), request.getParameter("status"), user, false);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer == 6) {
                
            }
            if (answer == 7) {
                request.setAttribute("commenta", 1);
            }
            if (answer == 8) {
                request.setAttribute("commento", 1);
            }
            if (answer == 9) {
                request.setAttribute("commento", 0);
            }
            if (answer == 10) {
                request.setAttribute("commento", 1);
                ms.addComment(request.getParameter("textcomm"), user, incident);
            }
            request.setAttribute("incident", incident);
            List<Comments> comments = ms.getComments(incident);
            request.setAttribute("comments", comments);
        }
        if ("/user/new_incident".equals(userPath)) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 2) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer == 1) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti, true, 0);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer == 4) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti, false, Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            getServletContext().setAttribute("editincident", 0);
            List<Typeincident> typs = ms.getAllTypesIncident("none");
            request.setAttribute("typs", typs);
        }
        if ("/user/closed_incidents".equals(userPath)) {
            getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user, "none"));
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

    private int checkAction(HttpServletRequest req) {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Cancel") != null) {
            return 2;
        }
        if (req.getParameter("Close") != null) {
            return 3;
        }
        if (req.getParameter("Edit") != null) {
            return 4;
        }
        if (req.getParameter("Done") != null) {
            return 5;
        }
        if (req.getParameter("Accept") != null) {
            return 6;
        }
        if (req.getParameter("NoAccept") != null) {
            return 7;
        }
        if (req.getParameter("bCommOn") != null) {
            return 8;
        }
        if (req.getParameter("bCommOff") != null) {
            return 9;
        }
        if (req.getParameter("bCommGo") != null) {
            return 10;
        }
        return 0;
    }

}
