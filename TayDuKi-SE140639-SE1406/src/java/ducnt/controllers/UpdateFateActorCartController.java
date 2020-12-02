/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.FateActorDetailDTO;
import ducnt.dtos.FateActorDetailErrorObj;
import ducnt.dtos.UserDTO;
import ducnt.models.FateActorCart;
import ducnt.utils.PropertiesUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngota
 */
public class UpdateFateActorCartController extends HttpServlet {

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
        String url = properties.getProperties("LOAD_DIRECTOR_FATE");
        boolean valid = true;
        try {
            HttpSession session = request.getSession();
            FateActorCart cart = (FateActorCart) session.getAttribute("FATE_ACTOR_CART");
            FateActorDetailErrorObj errorObj = new FateActorDetailErrorObj();
            String actorID = request.getParameter("txtActorID");
            String roleName = request.getParameter("txtRoleName");
            String roleDescription = request.getParameter("txtRoleDescription");
            if (roleName.isEmpty() || roleName.length() > 100) {
                valid = false;
                errorObj.setRoleNameError("Role name length is between 1 - 100");
            }
            if (roleDescription.isEmpty() || roleDescription.length() > 500) {
                valid = false;
                errorObj.setRoleDescriptionError("Role description length is between 1 - 500");
            }
            FateActorDetailDTO dto = new FateActorDetailDTO(null, actorID, roleName, roleDescription, cart.getDirectorID());
            if (valid) {
                cart.updateActorInCart(dto);
                session.setAttribute("FATE_ACTOR_CART", cart);
            } else {
                request.setAttribute("DTO", dto);
                request.setAttribute("INVALID", errorObj);
                url = properties.getProperties("FATE_ACTOR_EDIT_PAGE");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log("ERROR at UpdateFateActorCartController " + e.getMessage());
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
