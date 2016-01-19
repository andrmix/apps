/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Incidents;
import entity.Users;
import java.io.IOException;
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
@WebServlet(name = "specialist_controller",
        urlPatterns = {"/specialist", "/specialist/spec_incident_data",
            "/specialist/spec_done_incidents", "/specialist/spec_done_incident_data"})
public class specialist_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Users specialist = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", specialist);
        getServletContext().setAttribute("openIncidents", ms.getSpecialistOpenIncidents(specialist));
        getServletContext().setAttribute("doneIncidents", ms.getSpecialistDoneIncidents(specialist));
        getServletContext().setAttribute("closedIncidents", ms.getSpecialistClosedIncidents(specialist));

        if ("/specialist".equals(request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
        }

        if ("/specialist/spec_incident_data".equals(request.getServletPath())) {
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("incident", incident);
            request.setAttribute("done", 0);
            int answer = 0;
            answer = checkAction(request);
            if (incident.getStatus().equals(ms.getStatuses().get(5))) {
                request.setAttribute("status", 1);
            }
            if (incident.getStatus().equals(ms.getStatuses().get(4))) {
                request.setAttribute("status", 2);
            }
            if (answer == 1) {
                ms.inWork(incident);
                response.sendRedirect(request.getContextPath() + "/specialist");
                return;
            }
            if (answer == 2) {
                //Отклонить
            }
            if (answer == 3) {
                request.setAttribute("done", 1);
            }
            if (answer == 4) {
                ms.doneIncident(incident, request.getParameter("decision"));
                response.sendRedirect(request.getContextPath() + "/specialist");
                return;
            }
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incident_data.jsp").forward(request, response);
        }

        if ("/specialist/spec_done_incidents".equals(request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }

        if ("/specialist/spec_done_incident_data".equals(request.getServletPath())) {
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("incident", incident);
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incident_data.jsp").forward(request, response);
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

    private int checkAction(HttpServletRequest req) {
        if (req.getParameter("InWork") != null) {
            return 1;
        }
        if (req.getParameter("Close") != null) {
            return 2;
        }
        if (req.getParameter("Doit") != null) {
            return 3;
        }
        if (req.getParameter("Done") != null) {
            return 4;
        }
        return 0;
    }

}
