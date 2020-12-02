/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.utils.DBUtils;
import ducnt.dtos.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngota
 */
public class UserDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public UserDTO checkLogin(String username, String password) throws Exception {
        String fullname, phone, email, description, image, role;
        char sex;
        boolean status;
        UserDTO dto = null;
        try {
            String sql = "SELECT roleID, userFullname, userSex, userPhone, userEmail, userDescription, userImg, userStatus\n"
                    + "FROM dbo.tblUser\n"
                    + "WHERE userID=? AND userPassword=?";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("roleID");
                fullname = rs.getString("userFullname");
                sex = rs.getString("userSex").charAt(0);
                phone = rs.getString("userPhone");
                email = rs.getString("userEmail");
                description = rs.getString("userDescription");
                image = rs.getString("userImg");
                status = rs.getBoolean("userStatus");
                dto = new UserDTO(username, role, fullname, sex, phone, email, description, image, status);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return dto;
    }

    public boolean addUser(UserDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT dbo.tblUser(userID, roleID, userFullname, userSex, userPassword, userPhone, userEmail, userDescription, userImg, userStatus)\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUserID());
            preStm.setString(2, dto.getRoleID());
            preStm.setNString(3, dto.getFullname());
            preStm.setString(4, String.valueOf(dto.getSex()));
            preStm.setString(5, dto.getPassword());
            preStm.setString(6, dto.getPhoneNum());
            preStm.setString(7, dto.getEmail());
            preStm.setNString(8, dto.getUserDescription());
            preStm.setNString(9, dto.getUserImage());
            preStm.setBoolean(10, dto.isActive());
            check = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }

//    public List<UserDTO> findByLikeName(String search) throws Exception {
//        List<UserDTO> result = null;
//        String id = null, role = null, fullname = null;
//        String phoneNum = null, email = null, description = null;
//        String image = null;
//        char sex = 0;
//        UserDTO dto = null;
//        try {
//            String sql = "SELECT userID, roleID, userFullname, userSex, userPhone, userEmail, userDescription, userImg, userStatus\n"
//                    + "FROM dbo.tblUser\n"
//                    + "WHERE userFullname LIKE ? AND userID != 'admin' AND userStatus=1";
//            conn = DBUtils.makeConnection();
//            preStm = conn.prepareStatement(sql);
//            preStm.setNString(1, "%" + search + "%");
//            rs = preStm.executeQuery();
//            result = new ArrayList<>();
//            while (rs.next()) {
//                id = rs.getString("userID");
//                role = rs.getString("roleID");
//                fullname = rs.getString("userFullname");
//                sex = rs.getString("userSex").charAt(0);
//                phoneNum = rs.getString("userPhone");
//                email = rs.getString("userEmail");
//                description = rs.getString("userDescription");
//                image = rs.getString("userImg");
//                dto = new UserDTO(id, role, fullname, sex, phoneNum, email, description, image);
//                result.add(dto);
//            }
//        } finally {
//            DBUtils.closeConnection(conn, preStm, rs);
//        }
//        return result;
//    }
    public int getAmountOfFindByLikeName(String search) throws Exception {
        int amount = 0;
        try {
            String sql = "SELECT COUNT(userID) AS ResultCount\n"
                    + "FROM dbo.tblUser\n"
                    + "WHERE userFullname LIKE ? AND roleID != 'ADM' AND userStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            if (rs.next()) {
                amount = rs.getInt("ResultCount");
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return amount;
    }

    public List<UserDTO> findByLikeName(String search, int start, int total) throws Exception {
        List<UserDTO> result = null;
        String id = null, role = null, fullname = null;
        String phoneNum = null, email = null, description = null;
        String image = null;
        char sex = 0;
        UserDTO dto = null;
        try {
            String sql = "SELECT userID, roleID, userFullname, userSex, userPhone, userEmail, userDescription, userImg, userStatus\n"
                    + "FROM dbo.tblUser\n"
                    + "WHERE userFullname LIKE ? AND roleID != 'ADM' AND userStatus=1\n"
                    + "ORDER BY userFullname\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, "%" + search + "%");
            preStm.setInt(2, start);
            preStm.setInt(3, total);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("userID");
                role = rs.getString("roleID");
                fullname = rs.getString("userFullname");
                sex = rs.getString("userSex").charAt(0);
                phoneNum = rs.getString("userPhone");
                email = rs.getString("userEmail");
                description = rs.getString("userDescription");
                image = rs.getString("userImg");
                dto = new UserDTO(id, role, fullname, sex, phoneNum, email, description, image);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public List<UserDTO> findByLikeNameAndRole(String search, String roleID, int start, int total) throws Exception {
        List<UserDTO> result = null;
        String id = null, role = null, fullname = null;
        String phoneNum = null, email = null, description = null;
        String image = null;
        char sex = 0;
        UserDTO dto = null;
        try {
            String sql = "SELECT userID, roleID, userFullname, userSex, userPhone, userEmail, userDescription, userImg, userStatus\n"
                    + "FROM dbo.tblUser\n"
                    + "WHERE userFullname LIKE ? AND roleID != 'ADM' AND userStatus=1 AND roleID = ?\n"
                    + "ORDER BY userFullname\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, "%" + search + "%");
            preStm.setString(2, roleID);
            preStm.setInt(3, start);
            preStm.setInt(4, total);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("userID");
                role = rs.getString("roleID");
                fullname = rs.getString("userFullname");
                sex = rs.getString("userSex").charAt(0);
                phoneNum = rs.getString("userPhone");
                email = rs.getString("userEmail");
                description = rs.getString("userDescription");
                image = rs.getString("userImg");
                dto = new UserDTO(id, role, fullname, sex, phoneNum, email, description, image);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public int getAmountOfFindByLikeNameAndRole(String search, String roleID) throws Exception {
        int amount = 0;
        try {
            String sql = "SELECT COUNT(userID) AS ResultCount\n"
                    + "FROM dbo.tblUser\n"
                    + "WHERE userFullname LIKE ? AND roleID != 'ADM' AND userStatus=1 AND roleID=?";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, "%" + search + "%");
            preStm.setString(2, roleID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                amount = rs.getInt("ResultCount");
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return amount;
    }

    public UserDTO findByPrimaryKey(String id) throws Exception {
        UserDTO dto = null;
        String role = null, fullname = null;
        String phoneNum = null, email = null, description = null;
        String image = null;
        boolean status = true;
        char sex = 0;
        try {
            String sql = "SELECT roleID, userFullname, userSex, userPhone, userEmail, userDescription, userImg\n"
                    + "FROM dbo.tblUser\n"
                    + "WHERE userID=? AND userStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("roleID");
                fullname = rs.getString("userFullname");
                sex = rs.getString("userSex").charAt(0);
                phoneNum = rs.getString("userPhone");
                email = rs.getString("userEmail");
                description = rs.getString("userDescription");
                image = rs.getString("userImg");
                dto = new UserDTO(id, role, fullname, sex, phoneNum, email, description, image, status);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return dto;
    }

    public List<UserDTO> findByRoleID(String roleID) throws Exception {
        List<UserDTO> result = null;
        UserDTO dto = null;
        String id = null, fullname = null;
        String phoneNum = null, email = null, description = null;
        String image = null;
        boolean status = true;
        char sex = 0;
        try {
            String sql = "SELECT userID, userFullname, userSex, userPhone, userEmail, userDescription, userImg\n"
                    + "FROM dbo.tblUser\n"
                    + "WHERE roleID=? AND userStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, roleID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("userID");
                fullname = rs.getString("userFullname");
                sex = rs.getString("userSex").charAt(0);
                phoneNum = rs.getString("userPhone");
                email = rs.getString("userEmail");
                description = rs.getString("userDescription");
                image = rs.getString("userImg");
                dto = new UserDTO(id, roleID, fullname, sex, phoneNum, email, description, image, status);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean updateUser(UserDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE dbo.tblUser\n"
                    + "SET roleID=?, userFullname=?, userSex=?, userPassword=?, userPhone=?, userEmail=?, userDescription=?, userImg=?\n"
                    + "WHERE userID=? AND userStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getRoleID());
            preStm.setNString(2, dto.getFullname());
            preStm.setString(3, String.valueOf(dto.getSex()));
            preStm.setString(4, dto.getPassword());
            preStm.setString(5, dto.getPhoneNum());
            preStm.setString(6, dto.getEmail());
            preStm.setNString(7, dto.getUserDescription());
            preStm.setNString(8, dto.getUserImage());
            preStm.setString(9, dto.getUserID());
            check = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }

    public boolean deleteUser(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE dbo.tblUser "
                    + "SET userStatus=0 "
                    + "WHERE userID=?";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }
}
