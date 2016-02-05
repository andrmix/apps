/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Comments;
import entity.History;
import entity.Incidents;
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
@WebServlet(name = "specialist_controller",
        urlPatterns = {"/specialist", "/specialist/spec_incident_data",
            "/specialist/spec_done_incidents", "/specialist/spec_done_incident_data",
            "/specialist/spec_closed_incidents", "/specialist/statistic"})
public class specialist_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Users specialist = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", specialist);
        getServletContext().setAttribute("openIncidentsNew", ms.getSpecialistOpenIncidentsNew(specialist));
        
        //обращения =============================================================================================================
        if ("/specialist".equals(request.getServletPath())) {
            getServletContext().setAttribute("openIncidents", ms.getSpecialistOpenIncidents(specialist));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incidents.jsp").forward(request, response);
        }

        //данные обращения =============================================================================================================        
        if ("/specialist/spec_incident_data".equals(request.getServletPath())) {
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("incident", incident);
            request.setAttribute("done", 0);
            request.setAttribute("otmena", 0);
            request.setAttribute("commento", 1);
            request.setAttribute("ihistory", 0);
            String answer = null;
            answer = checkAction(request);
            
            if (incident.getNew1().equals(1) && (incident.getStatus().getId().equals(6) || incident.getStatus().getId().equals(5))) {
                ms.setNotNewIncident(incident);
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

            //комментарии
            if (answer.equals("bComm")) {
                request.setAttribute("commento", 1);
                request.setAttribute("ihistory", 0);
            }

            //комментировать
            if (answer.equals("bCommGo")) {
                request.setAttribute("commento", 1);
                request.setAttribute("ihistory", 0);
                ms.addComment(request.getParameter("textcomm"), specialist, incident);
            }

            //история
            if (answer.equals("bHist")) {
                request.setAttribute("ihistory", 1);
                request.setAttribute("commento", 0);
            }
            
            List<Comments> comments = ms.getComments(incident);
            request.setAttribute("comments", comments);
            List<History> history = ms.getHistory(incident);
            request.setAttribute("allhistory", history);
            request.getRequestDispatcher("/WEB-INF/specialist/spec_incident_data.jsp").forward(request, response);
        }

        //выполненные обращения ==================================================================================================
        if ("/specialist/spec_done_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("doneIncidents", ms.getSpecialistDoneIncidents(specialist));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_done_incidents.jsp").forward(request, response);
        }

        //закрытые обращения ==================================================================================================
        if ("/specialist/spec_closed_incidents".equals(request.getServletPath())) {
            getServletContext().setAttribute("closedIncidents", ms.getSpecialistClosedIncidents(specialist));
            request.getRequestDispatcher("/WEB-INF/specialist/spec_closed_incidents.jsp").forward(request, response);
        }
        
        //статистика ==================================================================================================
        if ("/specialist/statistic".equals(request.getServletPath())) {
            List stats = ms.getOneSpecialistsStatistics(specialist.getLogin());
            request.setAttribute("statList", stats);
            List statsYear1 = ms.getYearStatistic("2016", specialist.getLogin(), 1);
            List statsYear2 = ms.getYearStatistic("2016", specialist.getLogin(), 7);
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
        return "none";
    }

}
