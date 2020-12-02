/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.dtos;

import java.io.Serializable;

/**
 *
 * @author ngota
 */
public class UserDTO implements Serializable {

    private String userID;
    private String roleID;
    private String fullname;
    private char sex;
    private String password;
    private String phoneNum;
    private String email;
    private String userDescription;
    private String userImage;
    private boolean active;

    public UserDTO(String userID, String roleID, String fullname, char sex, String phoneNum, String email, String userDescription, String userImage, boolean active) {
        this.userID = userID;
        this.roleID = roleID;
        this.fullname = fullname;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.email = email;
        this.userDescription = userDescription;
        this.userImage = userImage;
        this.active = active;
    }

    public UserDTO(String userID, String roleID, String fullname, char sex, String phoneNum, String email, String userDescription, String userImage) {
        this.userID = userID;
        this.roleID = roleID;
        this.fullname = fullname;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.email = email;
        this.userDescription = userDescription;
        this.userImage = userImage;
        this.active = true;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", roleID=" + roleID + ", fullname=" + fullname + ", sex=" + sex + ", password=" + password + ", phoneNum=" + phoneNum + ", email=" + email + ", userDescription=" + userDescription + ", userImage=" + userImage + ", active=" + active + '}';
    }

    
}
