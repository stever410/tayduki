/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.models.FateBean;
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
public class SearchFateController extends HttpServlet {

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
        String url = properties.getProperties("SEARCH_FATE_PAGE");
        try {
            String search = request.getParameter("txtSearch");
            int currentPage = Integer.parseInt(request.getParameter("pageID"));
            int resultToScreen = 10;
            int recordToIgnored = (currentPage - 1) * resultToScreen;
            // recordToIgnored: so ket qua phai bo qua bang (trang hien tai - 1) * so ket qua mun in ra man hinh
            // vi du trang 2 thi (2-1) * 5 = 5 => Phai bo qua 5 ket qua
            FateBean beans = new FateBean();
            beans.setSearch(search);
            beans.findByLikeName(recordToIgnored, resultToScreen);
            // so trang bang tong so ket qua / so page muon hien thi
            int totalPageCount = (int) Math.ceil((double) beans.getAmountOfFindByLikeName() / resultToScreen);
            request.setAttribute("SEARCH_RESULT", beans.getListFates());
            request.setAttribute("SEARCH_RESULT_PAGE_COUNT", totalPageCount);
            request.setAttribute("CURRENT_PAGE", currentPage);
        } catch (Exception e) {
//            e.printStackTrace();
            log("ERROR at SearchFateController " + e.getMessage());
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
