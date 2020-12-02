/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.EquipmentDTO;
import ducnt.dtos.FateEquipmentDetailDTO;
import ducnt.dtos.UserDTO;
import ducnt.models.EquipmentBean;
import ducnt.models.FateEquipmentCart;
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
public class AddFateEquipmentCartController extends HttpServlet {

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
        String url = properties.getProperties("SEARCH_EQUIPMENT");
        try {
            HttpSession session = request.getSession();
            UserDTO currentUser = (UserDTO) session.getAttribute("USER_DTO");
            FateEquipmentCart cart = (FateEquipmentCart) session.getAttribute("FATE_EQUIPMENT_CART");
            if (cart == null) {
                cart = new FateEquipmentCart(currentUser.getUserID());
            }
            String equipmentID = request.getParameter("txtEquipmentID");
            EquipmentBean beans = new EquipmentBean();
            beans.setEquipmentID(equipmentID);
            EquipmentDTO equipmentDTO = beans.findByPrimaryKey();
            FateEquipmentDetailDTO fateEquipmentDTO = new FateEquipmentDetailDTO(null, equipmentID, 1, cart.getDirectorID());
            if (equipmentDTO.getEquipmentAmount() == 0) {
                // neu so luong dung cu trong cart > so luong trong kho
                request.setAttribute("OUT_OF_STOCK", "Equipment out of stock. Can't add");
            } else {
                cart.addEquipmentToCart(fateEquipmentDTO);
                if (cart.getCart().get(fateEquipmentDTO) > equipmentDTO.getEquipmentAmount()) {
                    cart.updateEquipmentInCart(equipmentID, cart.getCart().get(fateEquipmentDTO) - 1);
                    request.setAttribute("INVALID", "Can't add more");
                } else {
                    request.setAttribute("SUCCESS", "Add " + equipmentID + " to cart 2 success");
                }
            }
            session.setAttribute("FATE_EQUIPMENT_CART", cart);
        } catch (Exception e) {
//            e.printStackTrace();
            log("ERROR at AddFateEquipmentCartController " + e.getMessage());
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
