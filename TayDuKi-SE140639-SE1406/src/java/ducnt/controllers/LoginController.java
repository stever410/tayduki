/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.UserDTO;
import ducnt.models.UserBean;
import ducnt.utils.PropertiesUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngota
 */
public class LoginController extends HttpServlet {

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
        String url = properties.getProperties("LOGIN_PAGE");
        try {
            HttpSession session = request.getSession();
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");
            UserBean beans = new UserBean();
            beans.setUsername(userID);
            beans.setPassword(password);
            UserDTO dto = beans.checkLogin();
            if (dto == null) {
                request.setAttribute("INVALID", "Invalid username or password");
            } else {
                if (dto.isActive()) {
                    switch (dto.getRoleID()) {
                        case "ADM":
                            url = properties.getProperties("ADM_PAGE");
                            break;
                        case "ACT":
                            url = properties.getProperties("ACT_PAGE");
                            break;
                        case "DIR":
                            url = properties.getProperties("DIR_PAGE");
                            break;
                        default:
                            request.setAttribute("ERROR", "Invalid role");
                    }
                    session.setAttribute("USER_DTO", dto);
                } else {
                    request.setAttribute("ERROR", "Account have been disabled! Please contact admin for more information.");
                }
            }
        } catch (Exception e) {
            log("ERROR at LoginController " + e.getMessage());
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
