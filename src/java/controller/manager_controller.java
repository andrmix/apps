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
@WebServlet(name = "manager_controller",
        urlPatterns = {"/manager", "/manager/incident_data"})
public class manager_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Users manager = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", manager);
        
        if ("/manager".equals(request.getServletPath())) {
            getServletContext().setAttribute("unallocatedIncidents", ms.getUnallocatedIncidents());
            request.getRequestDispatcher("/WEB-INF/manager/unallocated_incidents.jsp").forward(request, response);
        }

        if ("/manager/incident_data".equals(request.getServletPath())) {
            Incidents incident = ms.findIncident(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("incident", incident);
            int answer = 0;
            answer = checkAction(request);
            getServletContext().setAttribute("appoint", 0);
            if (answer == 1) {
                getServletContext().setAttribute("specialists", ms.getSpecialists());
                getServletContext().setAttribute("appoint", 1);
            }
            if (answer == 4) {
                Users user = ms.findUser(request.getParameter("specId"));
                ms.addSpecialist(incident, user);
                response.sendRedirect(request.getContextPath() + "/manager");
                return;
            }
            if (answer == 3) {
                //Отклонить
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
        return 0;
    }
}
