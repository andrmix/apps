/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Departs;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "admin_controller", urlPatterns = {"/admin"})
public class admin_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if ("/admin".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            request.setAttribute("name", request.getUserPrincipal().getName());
            getServletContext().setAttribute("departs", ms.getAllDeparts());
            if ((answer == 0) || (answer == 2)) {
                getServletContext().setAttribute("userList", ms.getAllUsers());
                getServletContext().setAttribute("filtr", 0);
            }
            if (answer == 1) {
                Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("departId")));
                getServletContext().setAttribute("userList", ms.getUsersByDepart(depart));
                getServletContext().setAttribute("filtr", 1);
            }
            if (answer == 3) {
                String searchText = request.getParameter("Search");
                getServletContext().setAttribute("userList", ms.getUsersSearch(searchText));
                getServletContext().setAttribute("filtr", 0);
            }
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
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
        if (req.getParameter("Filteroff") != null) {
            return 1;
        }
        if (req.getParameter("Filteron") != null) {
            return 2;
        }
        if (req.getParameter("Searchb") != null) {
            return 3;
        }
        return 0;
    }

}
