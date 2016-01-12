/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Incidents;
import entity.Typeincident;
import entity.Users;
import java.io.IOException;
import java.util.Enumeration;
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
    "/user/new_incident", "/user/closed_incidents", "/user/done_incidents"})
public class incident_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userPath = request.getServletPath();
        if ("/user/user_incident".equals(userPath)) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 3) {
                Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
                ms.cancelIncident(incident);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer == 2) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            String id = null;
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                id = "id".equals(param) ? request.getParameter(param) : id;
            }
            try {
                Incidents incident = ms.findIncident(Integer.parseInt(id));
                request.setAttribute("incident", incident);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ("/user/new_incident".equals(userPath)) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 1) {
                Typeincident ti = ms.findTypeIncident(Integer.parseInt(request.getParameter("typId")));
                Users user = ms.findUser(request.getUserPrincipal().getName());
                ms.addIncident(request.getParameter("title"), request.getParameter("texti"), user, ti);
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            if (answer == 2) {
                response.sendRedirect(request.getContextPath() + "/user");
                return;
            }
            List<Typeincident> typs = ms.getTypesOfincidents();
            request.setAttribute("typs", typs);
        }
        if (("/user/closed_incidents".equals(userPath)) || ("/user/done_incidents".equals(userPath))) {
            Users user = ms.findUser(request.getUserPrincipal().getName());
            getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user));
            getServletContext().setAttribute("doneIncidents", ms.getDoneIncidents(user));
            getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user));
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
        return 0;
    }

}
