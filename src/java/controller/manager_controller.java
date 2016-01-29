package controller;

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
@WebServlet(name = "manager_controller",
        urlPatterns = {"/manager", "/manager/incident_data", "/manager/allocated",
            "/sort_by_name_un", "/sort_by_date_un", "/sort_by_status_un",
            "/sort_by_zay_un", "/sort_by_name_allo", "/sort_by_date_allo",
            "/sort_by_status_allo", "/sort_by_zay_allo", "/sort_by_spec_allo",
            "/manager/specialists", "/manager/specialist_data"})
public class manager_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Users manager = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", manager);
        getServletContext().setAttribute("unallocatedIncidents", ms.getUnallocatedIncidents("none"));
        getServletContext().setAttribute("unallocatedIncidentsNew", ms.getUnallocatedIncidentsNew());
        getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("none"));

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

        if ("/manager".equals(request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }

        if ("/sort_by_name_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("name"));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_date_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("date"));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_status_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("status"));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_zay_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("zay"));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }
        if ("/sort_by_spec_allo".equals(request.getServletPath())) {
            getServletContext().setAttribute("allocatedIncidents", ms.getAllocatedIncidents("spec"));
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }

        if ("/manager/allocated".equals(request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/manager/allocated_incidents.jsp").forward(request, response);
        }

        if ("/sort_by_fio_spec".equals(request.getServletPath())) {
            getServletContext().setAttribute("specialistList", ms.getSpecialistsStatistics());
            request.getRequestDispatcher("/WEB-INF/manager/specialists.jsp").forward(request, response);
        }

        if ("/manager/specialists".equals(request.getServletPath())) {
            getServletContext().setAttribute("specialistList", ms.getSpecialistsStatistics());
            request.getRequestDispatcher("/WEB-INF/manager/specialists.jsp").forward(request, response);
        }

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

        if ("/manager/incident_data".equals(request.getServletPath())) {
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            if (incident.getNew1().equals(1)  && incident.getStatus().getId().equals(1)) {
                ms.setNotNewIncident(incident);
            }
            String un = request.getParameter("un");
            request.setAttribute("incident", incident);
            request.setAttribute("un", un);
            getServletContext().setAttribute("commenta", 0);
            int answer = 0;
            answer = checkAction(request);
            getServletContext().setAttribute("appoint", 0);
            if (answer == 1) {
                getServletContext().setAttribute("specialists", ms.getSpecialists("none"));
                getServletContext().setAttribute("appoint", 1);
            }
            if (answer == 4) {
                Users user = ms.findUser(request.getParameter("specId"));
                ms.addSpecialist(incident, user);
                response.sendRedirect(request.getContextPath() + "/manager");
                return;
            }
            if (answer == 3) {
                getServletContext().setAttribute("commenta", 1);
            }
            if (answer == 5) {
                ms.cancelIncident(incident, request.getParameter("textc"), request.getParameter("status"), null, false);
                response.sendRedirect(request.getContextPath() + "/manager");
                return;
            }
            request.getRequestDispatcher("/WEB-INF/manager/incident_data.jsp").forward(request, response);
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
        if (req.getParameter("Appoint") != null) {
            return 1;
        }
        if (req.getParameter("Cancel") != null) {
            return 2;
        }
        if (req.getParameter("Close") != null) {
            return 3;
        }
        if (req.getParameter("Done") != null) {
            return 4;
        }
        if (req.getParameter("pDone") != null) {
            return 5;
        }
        return 0;
    }
}
