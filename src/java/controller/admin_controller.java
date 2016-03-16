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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.GetterBeanLocal;
import session.ManagementSystemLocal;

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

    @EJB
    private ManagementSystemLocal ms;
    
    @EJB
    private GetterBeanLocal gb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Users admin = ms.findUser(request.getUserPrincipal().getName());
        request.setAttribute("usera", admin);

        //users =============================================================================================================
        //сортировка
        if ("/sort_by_fio".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", gb.getAllUsers("fio"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }
        if ("/sort_by_login".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", gb.getAllUsers("login"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }
        if ("/sort_by_email".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", gb.getAllUsers("email"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }
        if ("/sort_by_depart".equals(request.getServletPath())) {
            getServletContext().setAttribute("userList", gb.getAllUsers("depart"));
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }

        //userlist =============================================================================================================
        if ("/admin".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            request.setAttribute("name", request.getUserPrincipal().getName());
            getServletContext().setAttribute("departs", gb.getAllDeparts("none"));
            getServletContext().setAttribute("tools", 0);
            getServletContext().setAttribute("filtr", 0);
            getServletContext().setAttribute("userList", gb.getAllUsers("none"));

            //выключить фильтр
            if ((answer.equals("none")) || (answer.equals("Filteron"))) {
                getServletContext().setAttribute("userList", gb.getAllUsers("none"));
                getServletContext().setAttribute("filtr", 0);
            }

            //включить фильтр
            if (answer.equals("Filteroff")) {
                Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("departId")));
                getServletContext().setAttribute("userList", gb.getUsersByDepart(depart));
                List<Departs> departs = gb.getDepartsForEdit(depart);
                request.setAttribute("departs", departs);
                getServletContext().setAttribute("filtr", 1);
            }

            //Поиск
            if (answer.equals("Searchb")) {
                String searchText = request.getParameter("Search");
                getServletContext().setAttribute("userList", gb.getUsersSearch(searchText));
                getServletContext().setAttribute("filtr", 0);
            }

            //открыть панель
            if (answer.equals("bToolsOn")) {
                getServletContext().setAttribute("tools", 1);
            }

            //закрыть панель
            if (answer.equals("bToolsOff")) {
                getServletContext().setAttribute("tools", 0);
            }

            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
        }

        //новый пользователь =============================================================================================================
        if ("/admin/new_user".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);

            //добавить
            if (answer.equals("Add")) {
                Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("departId")));
                ms.addUser(request.getParameter("login"), request.getParameter("fio"),
                        request.getParameter("email"), depart, request.getParameter("role"), true);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }

            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }

            //редактировать
            if (answer.equals("Edit")) {
                Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("departId")));
                ms.addUser(request.getParameter("login"), request.getParameter("fio"),
                        request.getParameter("email"), depart, request.getParameter("role"), false);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }

            List<Departs> departs = gb.getAllDeparts("none");
            request.setAttribute("departs", departs);
            request.getRequestDispatcher("/WEB-INF/admin/new_user.jsp").forward(request, response);
        }

        //данные пользователя =============================================================================================================
        if ("/admin/user_data".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            Users user = ms.findUser(request.getParameter("login"));
            request.setAttribute("user", user);

            //удалить пользователя
            if (answer.equals("Delete")) {
                ms.deleteUser(user);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }

            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            
            //редактировать
            if (answer.equals("Edit")) {
                List<Departs> departs = gb.getDepartsForEdit(user.getDepart());
                request.setAttribute("departs", departs);
                Groupuser groupuser = ms.findGroupuser(user);
                request.setAttribute("login", user.getLogin());
                request.setAttribute("fio", user.getName());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("role", groupuser.getName());
                request.setAttribute("editUser", 1);
                request.getRequestDispatcher("/WEB-INF/admin/new_user.jsp").forward(request, response);
            }
            
            //сброс пароля
            if (answer.equals("resetPass")) {
                ms.resetPassword(user);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            
            //блокировка пользователя
            if (answer.equals("blockUser")) {
                ms.blockUser(user);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            
            //разблокировка пользователя
            if (answer.equals("unblockUser")) {
                ms.resetPassword(user);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
            
            request.getRequestDispatcher("/WEB-INF/admin/user_data.jsp").forward(request, response);
        }

        //departs =============================================================================================================
        //сортировка
        if ("/sort_by_name_depart".equals(request.getServletPath())) {
            getServletContext().setAttribute("departList", gb.getAllDeparts("name"));
            request.getRequestDispatcher("/WEB-INF/admin/departs.jsp").forward(request, response);
        }

        //новый отдел =============================================================================================================
        if ("/admin/new_depart".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            
            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            
            //добавить
            if (answer.equals("Add")) {
                ms.addDepart(request.getParameter("nameDepart"), true, 0);
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            
            //редактировать
            if (answer.equals("Edit")) {
                ms.addDepart(request.getParameter("nameDepart"), false, Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            
            getServletContext().setAttribute("id", null);
            getServletContext().setAttribute("nameDepart", null);
            getServletContext().setAttribute("editDepart", 0);
            request.getRequestDispatcher("/WEB-INF/admin/new_depart.jsp").forward(request, response);
        }

        //список отделов =============================================================================================================
        if ("/admin/departs".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            getServletContext().setAttribute("departList", gb.getAllDeparts("none"));
            
            //поиск
            if (answer.equals("Searchb")) {
                String searchText = request.getParameter("Search");
                getServletContext().setAttribute("departList", gb.getDepartsSearch(searchText));
            }
            
            //открыть панель
            if (answer.equals("bToolsOn")) {
                getServletContext().setAttribute("tools", 1);
            }
            
            //закрыть панель
            if (answer.equals("bToolsOff")) {
                getServletContext().setAttribute("tools", 0);
            }
            
            request.getRequestDispatcher("/WEB-INF/admin/departs.jsp").forward(request, response);
        }

        //данные отдела =============================================================================================================
        if ("/admin/depart_data".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            Departs depart = ms.findDepart(Integer.parseInt(request.getParameter("id")));
            getServletContext().setAttribute("depart", depart);
            
            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }

            //удалить
            if (answer.equals("Delete")) {
                ms.deleteDepart(depart);
                response.sendRedirect(request.getContextPath() + "/admin/departs");
                return;
            }
            
            //редактировать
            if (answer.equals("Edit")) {
                getServletContext().setAttribute("id", depart.getId());
                getServletContext().setAttribute("nameDepart", depart.getName());
                getServletContext().setAttribute("editDepart", 1);
                request.getRequestDispatcher("/WEB-INF/admin/new_depart.jsp").forward(request, response);
            }
            
            request.getRequestDispatcher("/WEB-INF/admin/depart_data.jsp").forward(request, response);
        }

        //typesincident =============================================================================================================
        //сортировка
        if ("/sort_by_name_typeincident".equals(request.getServletPath())) {
            getServletContext().setAttribute("typesIncidentList", gb.getAllTypesIncident("name"));
            request.getRequestDispatcher("/WEB-INF/admin/typesincident.jsp").forward(request, response);
        }

        //новый тип обращения =============================================================================================================
        if ("/admin/new_typeincident".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            
            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            
            //добавить
            if (answer.equals("Add")) {
                ms.addTypeIncident(request.getParameter("nameTypeIncident"), true, 0);
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            
            //редактировать
            if (answer.equals("Edit")) {
                ms.addTypeIncident(request.getParameter("nameTypeIncident"), false, Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            
            getServletContext().setAttribute("id", null);
            getServletContext().setAttribute("nameTypeIncident", null);
            getServletContext().setAttribute("editTypeIncident", 0);
            request.getRequestDispatcher("/WEB-INF/admin/new_typeincident.jsp").forward(request, response);
        }

        //список типов обращений =============================================================================================================
        if ("/admin/typesincident".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            getServletContext().setAttribute("typesIncidentList", gb.getAllTypesIncident("none"));
            
            //поиск
            if (answer.equals("Searchb")) {
                String searchText = request.getParameter("Search");
                getServletContext().setAttribute("typesIncidentList", gb.getTypesIncidentSearch(searchText));
            }
            
            //открыть панель
            if (answer.equals("bToolsOn")) {
                getServletContext().setAttribute("tools", 1);
            }
            
            //закрыть панель
            if (answer.equals("bToolsOff")) {
                getServletContext().setAttribute("tools", 0);
            }
            
            request.getRequestDispatcher("/WEB-INF/admin/typesincident.jsp").forward(request, response);
        }

        //данные типа обращения =============================================================================================================
        if ("/admin/typeincident_data".equals(request.getServletPath())) {
            String answer = null;
            answer = checkAction(request);
            Typeincident typeincident = ms.findTypeIncident(Integer.parseInt(request.getParameter("id")));
            getServletContext().setAttribute("typeIncident", typeincident);
            
            //отмена
            if (answer.equals("Cancel")) {
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            
            //удалить
            if (answer.equals("Delete")) {
                ms.deleteTypeincident(typeincident);
                response.sendRedirect(request.getContextPath() + "/admin/typesincident");
                return;
            }
            
            //редактировать
            if (answer.equals("Edit")) {
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

    private String checkAction(HttpServletRequest req) {
        if (req.getParameter("Filteroff") != null) {
            return "Filteroff";
        }
        if (req.getParameter("Filteron") != null) {
            return "Filteron";
        }
        if (req.getParameter("Searchb") != null) {
            return "Searchb";
        }
        if (req.getParameter("Add") != null) {
            return "Add";
        }
        if (req.getParameter("Cancel") != null) {
            return "Cancel";
        }
        if (req.getParameter("Edit") != null) {
            return "Edit";
        }
        if (req.getParameter("Delete") != null) {
            return "Delete";
        }
        if (req.getParameter("bToolsOn") != null) {
            return "bToolsOn";
        }
        if (req.getParameter("bToolsOff") != null) {
            return "bToolsOff";
        }
        if (req.getParameter("resetPass") != null) {
            return "resetPass";
        }
        if (req.getParameter("blockUser") != null) {
            return "blockUser";
        }
        if (req.getParameter("unblockUser") != null) {
            return "unblockUser";
        }
        return "none";
    }

}
