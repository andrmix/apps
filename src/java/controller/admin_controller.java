/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Departs;
import entity.Groupuser;
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
@WebServlet(name = "admin_controller",
        urlPatterns = {"/admin",
            "/admin/new_user", "/admin/user_data",
            "/admin/new_depart", "/admin/departs",
            "/admin/depart_data", "/admin/new_typeincident",
            "/admin/typesincident", "/admin/typeincident_data",
            "/sort_by_fio", "/sort_by_login", "/sort_by_email",
            "/sort_by_depart", "/sort_by_name_depart",
            "/sort_by_name_typeincident"})
public class admin_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Users admin = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("user", admin);
        //users
        if ("/sort_by_fio".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", ms.getAllUsers("fio"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }
        if ("/sort_by_login".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", ms.getAllUsers("login"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }
        if ("/sort_by_email".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", ms.getAllUsers("email"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }
        if ("/sort_by_depart".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", ms.getAllUsers("depart"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }

        if ("/admin".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            request.setAttribute("name", request.getUserPrincipal().getName());
            getServletContext().setAttribute("departs", ms.getAllDeparts("none"));
            if ((answer == 0) || (answer == 2)) {
                getServletContext().setAttribute("userList", ms.getAllUsers("none"));
                getServletContext().setAttribute("filtr", 0);
            }
            if (answer == 1) {
                Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("departId")));
                getServletContext().setAttribute("userList", ms.getUsersByDepart(depart));
                List<Departs> departs = ms.getDepartsForEdit(depart);
                request.setAttribute("departs", departs);
                getServletContext().setAttribute("filtr", 1);
            }
            if (answer == 3) {
                String searchText = request.getParameter("Search");
                getServletContext().setAttribute("userList", ms.getUsersSearch(searchText));
                getServletContext().setAttribute("filtr", 0);
            }
            if (answer == 8) {
                getServletContext().setAttribute("tools", 1);
            }
            if (answer == 9) {
                getServletContext().setAttribute("tools", 0);
            }
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }

        if ("/admin/new_user".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 4) {
                Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("departId")));
                ms.addUser(request.getParameter("login"), request.getParameter("pass"),
                        request.getParameter("fio"), request.getParameter("email"), depart,
                        request.getParameter("role"), true);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            if (answer == 5) {
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            if (answer == 6) {
                Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("departId")));
                ms.addUser(request.getParameter("login"), request.getParameter("pass"),
                        request.getParameter("fio"), request.getParameter("email"), depart,
                        request.getParameter("role"), false);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            List<Departs> departs = ms.getAllDeparts("none");
            request.setAttribute("departs", departs);
            request.getRequestDispatcher("/WEB-INF/admin/new_user.jsp").forward(request, response);
        }

        if ("/admin/user_data".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 7) {
                Users user = ms.findUser(request.getParameter("login"));
                ms.deleteUser(user);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            if (answer == 5) {
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            String login = null;
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                login = "login".equals(param) ? request.getParameter(param) : login;
            }
            try {
                Users user = ms.findUser(login);
                request.setAttribute("user", user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (answer == 6) {
                Users user = ms.findUser(login);
                List<Departs> departs = ms.getDepartsForEdit(user.getDepart());
                request.setAttribute("departs", departs);
                Groupuser groupuser = ms.findGroupuser(user);
                request.setAttribute("login", user.getLogin());
                request.setAttribute("pass", user.getPass());
                request.setAttribute("fio", user.getName());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("role", groupuser.getName());
                request.setAttribute("editUser", 1);
                request.getRequestDispatcher("/WEB-INF/admin/new_user.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/admin/user_data.jsp").forward(request, response);
        }

        //departs
        if ("/sort_by_name_depart".equals(request.getServletPath())) {
            getServletContext().setAttribute("departList", ms.getAllDeparts("name"));
            request.getRequestDispatcher("/WEB-INF/admin/departs.jsp").forward(request, response);
        }

        if ("/admin/new_depart".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 5) {
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            if (answer == 4) {
                ms.addDepart(request.getParameter("nameDepart"), true, 0);
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            if (answer == 6) {
                ms.addDepart(request.getParameter("nameDepart"), false, Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            getServletContext().setAttribute("id", null);
            getServletContext().setAttribute("nameDepart", null);
            getServletContext().setAttribute("editDepart", 0);
            request.getRequestDispatcher("/WEB-INF/admin/new_depart.jsp").forward(request, response);
        }

        if ("/admin/departs".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            getServletContext().setAttribute("departList", ms.getAllDeparts("none"));
            if (answer == 3) {
                String searchText = request.getParameter("Search");
                getServletContext().setAttribute("departList", ms.getDepartsSearch(searchText));
            }
            if (answer == 8) {
                getServletContext().setAttribute("tools", 1);
            }
            if (answer == 9) {
                getServletContext().setAttribute("tools", 0);
            }
            request.getRequestDispatcher("/WEB-INF/admin/departs.jsp").forward(request, response);
        }

        if ("/admin/depart_data".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 5) {
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("id")));
            getServletContext().setAttribute("depart", depart);
            if (answer == 7) {
                ms.deleteDepart(depart);
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            if (answer == 6) {
                getServletContext().setAttribute("id", depart.getId());
                getServletContext().setAttribute("nameDepart", depart.getName());
                getServletContext().setAttribute("editDepart", 1);
                request.getRequestDispatcher("/WEB-INF/admin/new_depart.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/admin/depart_data.jsp").forward(request, response);
        }

        //typesincident
        if ("/sort_by_name_typeincident".equals(request.getServletPath())) {
            getServletContext().setAttribute("typesIncidentList", ms.getAllTypesIncident("name"));
            request.getRequestDispatcher("/WEB-INF/admin/typesincident.jsp").forward(request, response);
        }

        if ("/admin/new_typeincident".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 5) {
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            if (answer == 4) {
                ms.addTypeIncident(request.getParameter("nameTypeIncident"), true, 0);
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            if (answer == 6) {
                ms.addTypeIncident(request.getParameter("nameTypeIncident"), false, Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            getServletContext().setAttribute("id", null);
            getServletContext().setAttribute("nameTypeIncident", null);
            getServletContext().setAttribute("editTypeIncident", 0);
            request.getRequestDispatcher("/WEB-INF/admin/new_typeincident.jsp").forward(request, response);
        }

        if ("/admin/typesincident".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            getServletContext().setAttribute("typesIncidentList", ms.getAllTypesIncident("none"));
            if (answer == 3) {
                String searchText = request.getParameter("Search");
                getServletContext().setAttribute("typesIncidentList", ms.getTypesIncidentSearch(searchText));
            }
            if (answer == 8) {
                getServletContext().setAttribute("tools", 1);
            }
            if (answer == 9) {
                getServletContext().setAttribute("tools", 0);
            }
            request.getRequestDispatcher("/WEB-INF/admin/typesincident.jsp").forward(request, response);
        }

        if ("/admin/typeincident_data".equals(request.getServletPath())) {
            int answer = 0;
            answer = checkAction(request);
            if (answer == 5) {
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            Typeincident typeincident = ms.findTypeIncident(Integer.parseInt(request.getParameter("id")));
            getServletContext().setAttribute("typeIncident", typeincident);
            if (answer == 7) {
                ms.deleteTypeincident(typeincident);
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            if (answer == 6) {
                getServletContext().setAttribute("id", typeincident.getId());
                getServletContext().setAttribute("nameTypeIncident", typeincident.getName());
                getServletContext().setAttribute("editTypeIncident", 1);
                request.getRequestDispatcher("/WEB-INF/admin/new_typeincident.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/admin/typeincident_data.jsp").forward(request, response);
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
        if (req.getParameter("Add") != null) {
            return 4;
        }
        if (req.getParameter("Cancel") != null) {
            return 5;
        }
        if (req.getParameter("Edit") != null) {
            return 6;
        }
        if (req.getParameter("Delete") != null) {
            return 7;
        }
        if (req.getParameter("bToolsOn") != null) {
            return 8;
        }
        if (req.getParameter("bToolsOff") != null) {
            return 9;
        }
        return 0;
    }

}
