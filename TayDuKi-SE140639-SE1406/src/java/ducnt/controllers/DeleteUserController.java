/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.FateDTO;
import ducnt.models.FateBean;
import ducnt.models.UserBean;
import ducnt.utils.PropertiesUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngota
 */
public class DeleteUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PropertiesUtils properties = new PropertiesUtils();
        String url = properties.getProperties("SEARCH_USER_DETAIL");
        try {
            String userID = request.getParameter("txtUserID");
            UserBean beans = new UserBean();
            beans.setUsername(userID);
            if (beans.findByPrimaryKey().getRoleID().equals("DIR")) {
                FateBean fateBeans = new FateBean();
                fateBeans.setDirectorID(userID);
                if (fateBeans.findFateOfDirector().size() > 0) {
                    String warningText = "";
                    for(int i = 0; i < fateBeans.getListFates().size(); i++) {
                        if(i == fateBeans.getListFates().size() - 1) {
                            warningText += fateBeans.getListFates().get(i).getFateID();
                        } else {
                            warningText += fateBeans.getListFates().get(i).getFateID() + ",";
                        }
                    }
                    request.setAttribute("DELETE_FAIL", "Director in charge with fate " + warningText + ". Change them first");
                }
            } else {
                if (beans.deleteUser()) {
                    url = properties.getProperties("SEARCH_USER");
                    request.setAttribute("DELETE_SUCCESS", "Delete user success");
                }
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Can't delete this user");
            log("ERROR at DeleteUserController " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
