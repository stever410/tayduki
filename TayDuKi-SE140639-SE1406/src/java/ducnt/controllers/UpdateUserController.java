/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.controllers;

import ducnt.dtos.UserDTO;
import ducnt.dtos.UserErrorObj;
import ducnt.models.UserBean;
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
public class UpdateUserController extends HttpServlet {

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
        String url = properties.getProperties("USER_UPDATE_PAGE");
        String id = null, role = null, fullname = null, password = null;
        String confirm = null, phoneNum = null, email = null, description = null;
        String previousUserImage = null;
        char sex = 0;
        boolean valid = true;
        UserDTO dto = null;
        UserErrorObj errorObj = new UserErrorObj();
        FileItem imageItem = null;
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        try {
            // 1. get user update params
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
                id = (String) params.get("txtUserID");
                role = ((String) params.get("cbRoles")).substring(0, 3).toUpperCase();
                fullname = (String) params.get("txtFullname");
                sex = ((String) params.get("rdGender")).charAt(0);
                password = (String) params.get("txtPassword");
                confirm = (String) params.get("txtConfirm");
                phoneNum = (String) params.get("txtPhoneNum");
                email = (String) params.get("txtEmail");
                description = (String) params.get("txtDescription");
                previousUserImage = (String) params.get("txtPreviousUserImage");
                //1.5 validation
                if (fullname.isEmpty() || fullname.length() > 100) {
                    valid = false;
                    errorObj.setFullnameError("Fullname length must be between 1 - 100");
                }
                if (password.isEmpty() || id.length() > 100) {
                    valid = false;
                    errorObj.setPasswordError("Password length must be between 1 - 100");
                }
                if (!confirm.equals(password)) {
                    valid = false;
                    errorObj.setConfirmError("Confirm must match password");
                }
                if (!phoneNum.matches("^\\d{10}$")) {
                    valid = false;
                    errorObj.setPhoneNumError("Phone must be 10 number and start with 0. Ex:092345xxxx");
                }
                if (!email.matches("^[a-z][a-z0-9_]{1,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
                    valid = false;
                    errorObj.setEmailError("Invalid email");
                }
                if (!imageItem.getName().isEmpty()) {
                    if (!imageItem.getName().matches("([^\\s]+(\\.(?i)(jpe?g|png))$)")) {
                        valid = false;
                        errorObj.setImgError("Only .jpg, .jpeg, .png file is accepted");
                    }
                }
                if (description.length() > 500) {
                    valid = false;
                    errorObj.setDescriptionError("Description length must be between 0 - 500");
                }
                //2.
                dto = new UserDTO(id, role, fullname, sex, phoneNum, email, description, previousUserImage);
                if (valid) {
                    UserBean beans = new UserBean();
                    dto.setPassword(password);
                    if (!imageItem.getName().isEmpty()) {
                        String itemName = imageItem.getName();
                        // lay ten file
                        String fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                        String extension = fileName.substring(fileName.indexOf("."));
                        // ten luu tren server = userID + extension
                        String serverImageName = dto.getUserID() + extension;
                        // luu vao duong dan nay
                        dto.setUserImage(serverImageName);
                    }
                    if (beans.updateUser(dto)) {
                        String imagePath = getServletContext().getRealPath("/") + "images\\user\\" + dto.getUserImage();
                        String previousImagePath = getServletContext().getRealPath("/") + "images\\user\\" + previousUserImage;
                        // neu nguoi dung ko chon file thi khong can phai viet lai file tren server
                        if (!imageItem.getName().isEmpty()) {
                            File imageFile = new File(imagePath);
                            File previousImageFile = new File(previousImagePath);
                            // file ton tai roi thi xoa
                            previousImageFile.delete();
                            imageItem.write(imageFile);
                        }
                        // attribute luu du lieu nguoi dung search trc do
                        url = properties.getProperties("SEARCH_USER_DETAIL");
                        request.setAttribute("UPDATE_SUCCESS", "Update success");
                        request.setAttribute("USER_ID", dto.getUserID());
                    }
                } else {
                    request.setAttribute("INVALID", errorObj);
                    request.setAttribute("DTO", dto);
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if (e.getMessage().contains("Violation of UNIQUE KEY constraint 'UQ__tblUser__E19C969106317017")) {
                errorObj.setPhoneNumError("Phone number has already been used");
            }
            if (e.getMessage().contains("Violation of UNIQUE KEY constraint 'UQ__tblUser__D54ADF559858FF5C'")) {
                errorObj.setEmailError("Email has already been used");
            }
            if (dto != null) {
                request.setAttribute("DTO", dto);
            }
            request.setAttribute("INVALID", errorObj);
            log("ERROR at UpdateUserController " + e.getMessage());
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
