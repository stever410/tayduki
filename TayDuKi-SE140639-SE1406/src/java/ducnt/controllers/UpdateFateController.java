/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.FateDTO;
import ducnt.dtos.FateErrorObj;
import ducnt.models.FateBean;
import ducnt.utils.PropertiesUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UpdateFateController extends HttpServlet {

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
        String url = properties.getProperties("FATE_UPDATE_PAGE");
        String fateID, fateName, fateDescription, fateShootPlace;
        Date fateStartTime = null, fateEndTime = null;
        String fateDirectorID;
        int fateShootCount = 0;
        boolean valid = true;
        FateDTO dto = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        FateErrorObj errorObj = new FateErrorObj();
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        String previousFileName = null;
        FileItem uploadedFile = null;
        String destPage = null;
        try {
            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                items = upload.parseRequest(request);
                Hashtable params = new Hashtable();
                for (Object listItem : items) {
                    FileItem item = (FileItem) listItem;
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString("UTF-8"));
                    } else {
                        uploadedFile = item;
                    }
                }
                fateID = (String) params.get("txtFateID");
                fateName = (String) params.get("txtFateName");
                fateDescription = (String) params.get("txtFateDescription");
                fateShootPlace = (String) params.get("txtFateShootPlace");
                fateDirectorID = (String) params.get("cbDirectors");
                previousFileName = (String) params.get("txtPreviousFile");
                destPage = (String) params.get("txtDestPage");
                //1.5 validation
                if (fateID.isEmpty() || fateID.length() > 100) {
                    valid = false;
                    errorObj.setFateIDError("Fate ID length must be between 1 - 100");
                }
                if (fateName.isEmpty() || fateName.length() > 100) {
                    valid = false;
                    errorObj.setFateNameError("Fate name length must be between 1 - 100");
                }
                if (fateDescription.length() > 500) {
                    valid = false;
                    errorObj.setFateDescriptionError("Description length must be between 0 - 500");
                }
                if (fateShootPlace.length() > 500) {
                    valid = false;
                    errorObj.setFateDescriptionError("Shoot place length must be between 0 - 500");
                }
                //2.
                dto = new FateDTO(fateID, fateName, fateDescription, fateShootPlace, null, null, fateShootCount, null, fateDirectorID);
                fateShootCount = Integer.parseInt((String) params.get("txtFateShootCount"));
                dto.setFateShootCount(fateShootCount);
                fateStartTime = formatter.parse((String) params.get("txtFateStartTime"));
                dto.setFateStartTime(fateStartTime);
                fateEndTime = formatter.parse((String) params.get("txtFateEndTime"));
                dto.setFateEndTime(fateEndTime);
                if (fateShootCount <= 0 || fateShootCount > Integer.MAX_VALUE) {
                    valid = false;
                    errorObj.setFateShootCountError("Shoot count must larger than 0 and less than " + Integer.MAX_VALUE);
                }
                if (fateStartTime.after(fateEndTime)) {
                    valid = false;
                    errorObj.setFateEndTimeError("End time must be larger than start time");
                }
                if (valid) {
                    if (uploadedFile != null && !uploadedFile.getName().isEmpty()) {
                        String itemName = uploadedFile.getName();
                        // lay ten file
                        String fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                        String extension = fileName.substring(fileName.indexOf("."));
                        String uploadedFileName = dto.getFateID() + extension;
                        // luu vao duong dan nay
                        dto.setFateRequirementFile(uploadedFileName);
                    }
                    FateBean beans = new FateBean();
                    if (beans.updateFate(dto)) {
                        String previousImagePath = getServletContext().getRealPath("/") + "files\\" + previousFileName;
                        String requirementFilePath = getServletContext().getRealPath("/") + "files\\" + dto.getFateRequirementFile();
                        // neu nguoi dung ko chon file thi khong can phai viet lai file tren server
                        if (!uploadedFile.getName().isEmpty()) {
                            File requirementFile = new File(requirementFilePath);
                            File previousImageFile = new File(previousImagePath);
                            previousImageFile.delete();
                            uploadedFile.write(requirementFile);
                        }
                        url = properties.getProperties("SEARCH_FATE_DETAIL");
                        request.setAttribute("UPDATE_SUCCESS", "Update success");
                        request.setAttribute("FATE_ID", dto.getFateID());
                    }
                } else {
                    request.setAttribute("INVALID", errorObj);
                    request.setAttribute("DTO", dto);
                    request.setAttribute("txtDestPage", destPage);
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if (e.getMessage().contains("For input string")) {
                errorObj.setFateShootCountError("Invalid number");
            }
            if (e.getMessage().contains("Unparseable date")) {
                errorObj.setFateStartTimeError("Invalid start date");
                errorObj.setFateEndTimeError("Invalid end date");
            }
            request.setAttribute("INVALID", errorObj);
            request.setAttribute("txtDestPage", destPage);
            if (dto != null) {
                request.setAttribute("DTO", dto);
            }
            url = properties.getProperties("LOAD_FATE_DIRECTOR");
            log("ERROR at UpdateFateController " + e.getMessage());
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
