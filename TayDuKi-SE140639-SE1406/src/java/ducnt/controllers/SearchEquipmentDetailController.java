/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.EquipmentDTO;
import ducnt.dtos.UserDTO;
import ducnt.models.EquipmentBean;
import ducnt.utils.PropertiesUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ngota
 */
public class SearchEquipmentDetailController extends HttpServlet {

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
        String url = properties.getProperties("ERROR_PAGE");
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        String id;
        try {
            if (isMultiPart) {
                id = (String) request.getAttribute("EQUIPMENT_ID");
            } else {
                id = request.getParameter("txtEquipmentID");
            }
            EquipmentBean beans = new EquipmentBean();
            beans.setEquipmentID(id);
            EquipmentDTO dto = beans.findByPrimaryKey();
            request.setAttribute("DTO", dto);
            HttpSession session = request.getSession();
            UserDTO currentUser = (UserDTO) session.getAttribute("USER_DTO");
            String role = currentUser.getRoleID();
            switch (role) {
                case "ADM":
                    url = properties.getProperties("EQUIPMENT_DETAIL_PAGE");
                    break;
                case "DIR":
                    url = properties.getProperties("DIR_EQUIPMENT_DETAIL_PAGE");
                    break;
                case "ACT":
                    break;
                default:
                    request.setAttribute("ERROR", "Invalid role!");
                    break;
            }
        } catch (Exception e) {
            log("ERROR at SearchEquipmentDetailController " + e.getMessage());
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
