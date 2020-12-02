/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.NotificationDAO;
import ducnt.dtos.NotificationDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ngota
 */
public class NotificationBean implements Serializable {

    private NotificationDAO dao = null;
    private String userID = null;
    private List<NotificationDTO> listNotifications = null;

    public List<NotificationDTO> getListNotifications() {
        return listNotifications;
    }

    public void setListNotifications(List<NotificationDTO> listNotifications) {
        this.listNotifications = listNotifications;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public NotificationBean() {
        dao = new NotificationDAO();
    }

    public List<NotificationDTO> getTop10NotificationByUserID() throws Exception {
        return listNotifications = dao.getTop10NotificationByUserID(userID);
    }
}
