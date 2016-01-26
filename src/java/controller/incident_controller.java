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
            String answer = null;
            answer = checkAction(request);
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer.equals("Close")) {
                request.setAttribute("commenta", 1);
            }
            if (answer.equals("Edit")) {
                List<Typeincident> typs = ms.getTypesIncidentsForEdit(incident.getTypeIncident());
                request.setAttribute("incident", incident);
                request.setAttribute("typs", typs);
                request.setAttribute("editincident", 1);
                request.getRequestDispatcher("/WEB-INF/user/new_incident.jsp").forward(request, response);
            }
            if (answer.equals("Done")) {
                ms.cancelIncident(incident, request.getParameter("textc"), request.getParameter("status"), user, false);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer.equals("Accept")) {
                
            }
            if (answer.equals("NoAccept")) {
                request.setAttribute("commenta", 1);
            }
            if (answer.equals("bCommOn")) {
                request.setAttribute("commento", 1);
            }
            if (answer.equals("bCommOff")) {
                request.setAttribute("commento", 0);
            }
            if (answer.equals("bCommGo")) {
                request.setAttribute("commento", 1);
                ms.addComment(request.getParameter("textcomm"), user, incident);
            }
            request.setAttribute("incident", incident);
            List<Comments> comments = ms.getComments(incident);
            request.setAttribute("comments", comments);
        }
        if ("/user/new_incident".equals(userPath)) {
            String answer = null;
            answer = checkAction(request);
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer.equals("Add")) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti, true, 0);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer.equals("Edit")) {
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
        if (req.getParameter("bCommOn") != null) {
            return "bCommOn";
        }
        if (req.getParameter("bCommOff") != null) {
            return "bCommOff";
        }
        if (req.getParameter("bCommGo") != null) {
            return "bCommGo";
        }
        return "none";
    }

}
