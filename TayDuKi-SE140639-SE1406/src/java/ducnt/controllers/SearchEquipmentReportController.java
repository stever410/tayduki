/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.models.EquipmentReportBean;
import ducnt.utils.PropertiesUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngota
 */
public class SearchEquipmentReportController extends HttpServlet {

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
        String url = properties.getProperties("EQUIPMENT_REPORT_PAGE");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startTime = null, endTime = null;
        boolean status = true;
        try {
            status = request.getParameter("rdStatus").equals("true");
            startTime = formatter.parse(request.getParameter("txtStartTime"));
            endTime = formatter.parse(request.getParameter("txtEndTime"));
            // recordToIgnored: so ket qua phai bo qua bang (trang hien tai - 1) * so ket qua mun in ra man hinh
            // vi du trang 2 thi (2-1) * 5 = 5 => Phai bo qua 5 ket qua
            // so trang bang tong so ket qua / so page muon hien thi
            if (startTime.before(endTime)) {
                EquipmentReportBean beans = new EquipmentReportBean();
                beans.setActive(status);
                beans.setStartTime(startTime);
                beans.setEndTime(endTime);
                int currentPage = Integer.parseInt(request.getParameter("pageID"));
                int resultToScreen = 10;
                int recordToIgnored = (currentPage - 1) * resultToScreen;
                beans.findEquipmentsByDateAndStatus(recordToIgnored, resultToScreen);
                int totalPageCount = (int) Math.ceil((double) beans.getAmountOfFindEquipmentsByDateAndStatus() / resultToScreen);
                request.setAttribute("REPORT_PAGE_COUNT", totalPageCount);
                request.setAttribute("CURRENT_PAGE", currentPage);
                request.setAttribute("REPORT", beans.getEquipmentsReportList());
            } else {
                request.setAttribute("ERROR", "Start time must before end time");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if (e.getMessage().contains("Unparseable date")) {
                request.setAttribute("ERROR", "Invalid date");
            }
            log("ERROR at SearchEquipmentReportController " + e.getMessage());
        } finally {
            request.setAttribute("START_TIME", startTime);
            request.setAttribute("END_TIME", endTime);
            request.setAttribute("STATUS", status);
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
