/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.UserDAO;
import ducnt.dtos.UserDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ngota
 */
public class UserBean implements Serializable {

    private String username, password;
    private UserDAO dao;
    private List<UserDTO> listUsers;
    private String search;
    private String roleID;

    public UserBean() {
        dao = new UserDAO();
    }

    public boolean addUser(UserDTO dto) throws Exception {
        return dao.addUser(dto);
    }

    public boolean updateUser(UserDTO dto) throws Exception {
        return dao.updateUser(dto);
    }

    public int getAmountOfFindByLikeName() throws Exception {
        return dao.getAmountOfFindByLikeName(search);
    }

    public int getAmountOfFindByLikeNameAndRole() throws Exception {
        return dao.getAmountOfFindByLikeNameAndRole(search, roleID);
    }

    public List<UserDTO> findByLikeName(int start, int total) throws Exception {
        return listUsers = dao.findByLikeName(search, start, total);
    }

    public List<UserDTO> findByLikeNameAndRole(int start, int total) throws Exception {
        return listUsers = dao.findByLikeNameAndRole(search, roleID, start, total);
    }
//    public void findByLikeName() throws Exception {
//        listUsers = dao.findByLikeName(search);
//    }

    public UserDTO findByPrimaryKey() throws Exception {
        return dao.findByPrimaryKey(username);
    }

    public List<UserDTO> findByRole() throws Exception {
        return listUsers = dao.findByRoleID(roleID);
    }

    public UserDTO checkLogin() throws Exception {
        return dao.checkLogin(username, password);
    }

    public boolean deleteUser() throws Exception {
        return dao.deleteUser(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserDTO> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<UserDTO> listUsers) {
        this.listUsers = listUsers;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

}
