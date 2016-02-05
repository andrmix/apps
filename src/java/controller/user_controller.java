/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Users;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ManagementSystemLocal;

/**
 *
 * @author admin
 */
@WebServlet(name = "user_controller",
        urlPatterns = {"/", "/user", "/logout", "/change_password"})
public class user_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //корень =============================================================================================================
        if ("/".equals(request.getServletPath())) {
            response.sendRedirect("user");
        }

        //выход =============================================================================================================
        if ("/logout".equals(request.getServletPath())) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("/help");
            return;
        }

        //смена пароля =============================================================================================================
        if ("/change_password".equals(request.getServletPath())) {
            getServletContext().setAttribute("no", 0);

            //отмена
            if (request.getParameter("cancel") != null) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("/help");
                return;
            }

            //сброс пароля
            if (request.getParameter("resetPass") != null) {
                String pass1 = request.getParameter("pass1");
                String pass2 = request.getParameter("pass2");
                if (pass1.equals(pass2)) {
                    Users user = ms.findUser(request.getUserPrincipal().getName());
                    ms.setNewPassword(user, pass1);
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    response.sendRedirect("/help");
                    return;
                } else {
                    getServletContext().setAttribute("no", 1);
                }
            }

            request.getRequestDispatcher("/WEB-INF/change_password.jsp").forward(request, response);
        }

        //пользователь =============================================================================================================
        if (request.isUserInRole("user")) {
            Users user = ms.findUser(request.getUserPrincipal().getName());
            if (ms.isChangePassword(user)) {
                response.sendRedirect("change_password");
            } else {
                request.setAttribute("user", user);
                getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user, "none"));
                getServletContext().setAttribute("openIncidentsNew", ms.getOpenIncidentsNew(user));
                getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user, "none"));
                getServletContext().setAttribute("closedIncidentsNew", ms.getClosedIncidentsNew(user));
                request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
            }
            
            //админ ============================================================================================================= 
        } else if (request.isUserInRole("admin")) {
            response.sendRedirect("admin");
            
            //руководитель =============================================================================================================
        } else if (request.isUserInRole("manager")) {
            Users manager = ms.findUser(request.getUserPrincipal().getName());
            if (ms.isChangePassword(manager)) {
                response.sendRedirect("change_password");
            } else {
                response.sendRedirect("manager");
            }
            
            //специалист =============================================================================================================
        } else if (request.isUserInRole("specialist")) {
            Users specialist = ms.findUser(request.getUserPrincipal().getName());
            if (ms.isChangePassword(specialist)) {
                response.sendRedirect("change_password");
            } else {
                response.sendRedirect("specialist");
            }
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

}
