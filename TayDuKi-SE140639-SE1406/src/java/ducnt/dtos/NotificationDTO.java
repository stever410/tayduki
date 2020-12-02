/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngota
 */
public class NotificationDTO implements Serializable {

    private int notificationID;
    private String userID;
    private String notificationDescription;
    private boolean notificationStatus;
    private String commiterID;
    private Date createdTime;

    public NotificationDTO(int notificationID, String userID, String noficationDescription, boolean notificationStatus, String commiterID, Date createdTime) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.notificationDescription = noficationDescription;
        this.notificationStatus = notificationStatus;
        this.commiterID = commiterID;
        this.createdTime = createdTime;
    }

    public NotificationDTO(String userID, String noficationDescription, String commiterID, Date createdTime) {
        this.userID = userID;
        this.notificationDescription = noficationDescription;
        this.commiterID = commiterID;
        this.createdTime = createdTime;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public boolean isNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(boolean notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getCommiterID() {
        return commiterID;
    }

    public void setCommiterID(String commiterID) {
        this.commiterID = commiterID;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" + "notificationID=" + notificationID + ", userID=" + userID + ", noficationDescription=" + notificationDescription + ", notificationStatus=" + notificationStatus + ", commiterID=" + commiterID + '}';
    }

}
