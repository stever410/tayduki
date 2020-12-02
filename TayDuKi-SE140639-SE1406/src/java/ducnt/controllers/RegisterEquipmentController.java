/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.EquipmentDTO;
import ducnt.dtos.EquipmentErrorObj;
import ducnt.models.EquipmentBean;
import ducnt.utils.PropertiesUtils;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ngota
 */
public class RegisterEquipmentController extends HttpServlet {

    private static final String DEFAULT_ICON = "default.png";

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
        PropertiesUtils properties = new PropertiesUtils();
        String url = properties.getProperties("EQUIPMENT_REGISTRATION_PAGE");
        String equipmentID, equipmentName, equipmentDescription;
        int equipmentAmount = 0;
        boolean valid = true;
        EquipmentDTO dto = null;
        EquipmentErrorObj errorObj = new EquipmentErrorObj();
        FileItem imageItem = null;
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        try {
            // 1. get user registration params
            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                // lay du lieu tu request truyen vao list
                items = upload.parseRequest(request);
                Hashtable params = new Hashtable();
                for (Object listItem : items) {
                    FileItem item = (FileItem) listItem;
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString("UTF-8"));
                    } else {
                        imageItem = item;
                    }
                }
                equipmentID = (String) params.get("txtEquipmentID");
                equipmentName = (String) params.get("txtEquipmentName");
                equipmentDescription = (String) params.get("txtEquipmentDescription");
                //1.5 validation
                if (equipmentID.isEmpty() || equipmentID.length() > 100) {
                    valid = false;
                    errorObj.setEquipmentIDError("Equipment ID length must be between 1 - 100");
                }
                if (equipmentName.isEmpty() || equipmentName.length() > 100) {
                    valid = false;
                    errorObj.setEquipmentNameError("Equipment name length must be between 1 - 100");
                }
                if (!imageItem.getName().isEmpty()) {
                    if (!imageItem.getName().matches("([^\\s]+(\\.(?i)(jpe?g|png))$)")) {
                        valid = false;
                        errorObj.setEquipmentImageError("Only .jpg, .jpeg, .png file is accepted");
                    }
                }
                if (equipmentDescription.length() > 500) {
                    valid = false;
                    errorObj.setEquipmentDescriptionError("Description length must be between 0 - 500");
                }
                //2.
                dto = new EquipmentDTO(equipmentID, equipmentName, equipmentDescription, DEFAULT_ICON, equipmentAmount);
                equipmentAmount = Integer.parseInt((String) params.get("txtEquipmentAmount"));
                if (equipmentAmount <= 0 || equipmentAmount > Integer.MAX_VALUE) {
                    valid = false;
                    errorObj.setEquipmentAmountError("Amount must larger than 0 and less than " + Integer.MAX_VALUE);
                }
                if (valid) {
                    dto.setEquipmentAmount(equipmentAmount);
                    EquipmentBean beans = new EquipmentBean();
                    if (!imageItem.getName().isEmpty()) {
                        String itemName = imageItem.getName();
                        // lay ten file
                        String fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                        String extension = fileName.substring(fileName.indexOf("."));
                        String serverImageName = dto.getEquipmentID() + extension;
                        // luu vao duong dan nay
                        dto.setEquipmentImage(serverImageName);
                    }
                    if (beans.addEquipment(dto)) {
                        // neu nguoi dung ko chon file thi khong can phai viet lai file tren server
                        if (!imageItem.getName().isEmpty()) {
                            String imagePath = getServletContext().getRealPath("/") + "images\\equipment\\" + dto.getEquipmentImage();
                            File imageFile = new File(imagePath);
                            imageItem.write(imageFile);
                        }
                        request.setAttribute("REGISTRATION_SUCCESS", "Registration success");
                    }
                } else {
                    request.setAttribute("INVALID", errorObj);
                    request.setAttribute("DTO", dto);
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if (e.getMessage().contains("Violation of PRIMARY KEY constraint")) {
                errorObj.setEquipmentIDError("Equipment ID has already exist");
            }
            if(e.getMessage().contains("For input string")) {
                errorObj.setEquipmentAmountError("Invalid number");
            }
            request.setAttribute("INVALID", errorObj);
            if (dto != null) {
                request.setAttribute("DTO", dto);
            }
            log("ERROR at RegisterEquipmentController " + e.getMessage());
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
