/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Typeincident;
import entity.Users;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import session.ManagementSystemLocal;

public class upload_controller extends HttpServlet {

    @EJB(name = "ManagementSystem")
    private ManagementSystemLocal ms;

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 5000 * 1024;
    private int maxMemSize = 400 * 1024;
    private File file;
    private String title = "";
    private String texti = "";
    private String typId = "";
    private String userName = "";
    private String id = "";

    @Override
    public void init() throws ServletException {
        if (isWindows()) {
            filePath = getServletContext().getInitParameter("file-upload-windows");
        }
        if (isLinux()) {
            filePath = getServletContext().getInitParameter("file-upload-linux");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html;charset=UTF-8");
        java.io.PrintWriter out = response.getWriter();
        if (!isMultipart) {
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);
        List fileItems = null;

        try {
            fileItems = upload.parseRequest(request);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        Iterator i = fileItems.iterator();
        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (fi.isFormField()) {
                String name = fi.getFieldName();
                String value = fi.getString();
                
                //данные
                if (name.equals("title")) {
                    title = value;
                }
                if (name.equals("texti")) {
                    texti = value;
                }
                if (name.equals("typId")) {
                    typId = value;
                }
                if (name.equals("id")) {
                    id = value;
                }

                //отмена
                if (name.equals("Cancel")) {
                    request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
                }
                
                //добавить или редактировать
                if (name.equals("Add") || name.equals("Edit")) {
                    String path = null;
                    String fName = null;
                    Iterator it = fileItems.iterator();
                    while (it.hasNext()) {
                        FileItem item = (FileItem) it.next();
                        if (!item.isFormField()) {
                            String fieldName = item.getFieldName();
                            String fileName = item.getName();
                            String contentType = item.getContentType();
                            boolean isInMemory = item.isInMemory();
                            long sizeInBytes = item.getSize();
                            Long imageId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
                            if (fileName.lastIndexOf("\\") >= 0) {
                                fName = imageId + "." + fileName.substring(fileName.lastIndexOf("."));
                                path = filePath + fName;
                                file = new File(path);
                            } else {
                                fName = imageId + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
                                path = filePath + fName;
                                file = new File(path);
                            }
                            try {
                                item.write(file);
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                    Users user = ms.findUser(request.getUserPrincipal().getName());
                    Typeincident ti = ms.findTypeIncident(Integer.parseInt(typId));
                    
                    //добавить
                    if (name.equals("Add")) {
                        ms.addIncident(title, texti, user, ti, true, 0, fName);
                    }
                    
                    //редактировать
                    if (name.equals("Edit")) {
                        ms.addIncident(title, texti, user, ti, false, Integer.parseInt(id), fName);
                    }
                    
                    getServletContext().setAttribute("openIncidents", ms.getOpenIncidents(user, "none"));
                    getServletContext().setAttribute("openIncidentsNew", ms.getOpenIncidentsNew(user));
                    getServletContext().setAttribute("closedIncidents", ms.getClosedIncidents(user, "none"));
                    request.getRequestDispatcher("/WEB-INF/user/my_incidents.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("win") >= 0);
    }

    public static boolean isLinux() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
    }
}
