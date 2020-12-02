/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.models.EquipmentBean;
import ducnt.models.FateEquipmentCart;
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
public class RegisterFateEquipmentCartController extends HttpServlet {

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
        String fateID = request.getParameter("cbDirectorFate");
        try {
            HttpSession session = request.getSession();
            FateEquipmentCart cart = (FateEquipmentCart) session.getAttribute("FATE_EQUIPMENT_CART");
            EquipmentBean beans = new EquipmentBean();
            if (cart != null) {
                cart.setFateID(fateID);
                beans.updateEquipmentAmount(cart.getCart());
                cart.insertFateEquipmentCart();
                request.setAttribute("SUCCESS", "Add equipments to fate " + fateID + " success");
                session.setAttribute("FATE_EQUIPMENT_CART", null);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if(e.getMessage().contains("CK__tblEquipm__equip__29572725")) {
                request.setAttribute("ERROR", "Sorry. A product in your cart just recently out of stock");
            }
            url = properties.getProperties("LOAD_DIRECTOR_FATE");
            log("ERROR at RegisterFateEquipmentCartController " + e.getMessage());
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
