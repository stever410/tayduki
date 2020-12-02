/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.dtos.EquipmentDTO;
import ducnt.dtos.FateEquipmentDetailDTO;
import ducnt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngota
 */
public class EquipmentDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public boolean addEquipment(EquipmentDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT dbo.tblEquipment(equipmentID, equipmentName, equipmentDescription, equipmentImg, equipmentAmount, equipmentStatus)\n"
                    + "VALUES(?,?,?,?,?,?)";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getEquipmentID());
            preStm.setNString(2, dto.getEquipmentName());
            preStm.setNString(3, dto.getEquipmentDescription());
            preStm.setString(4, dto.getEquipmentImage());
            preStm.setInt(5, dto.getEquipmentAmount());
            preStm.setBoolean(6, dto.isActive());
            check = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }

//    public List<EquipmentDTO> findByLikeName(String search) throws Exception {
//        List<EquipmentDTO> result = null;
//        String id, name, description, img;
//        int amount;
//        EquipmentDTO dto = null;
//        try {
//            String sql = "SELECT equipmentID, equipmentName, equipmentDescription, equipmentImg, equipmentAmount \n"
//                    + "FROM dbo.tblEquipment\n"
//                    + "WHERE equipmentName=? AND equipmentStatus=1";
//            conn = DBUtils.makeConnection();
//            preStm = conn.prepareStatement(sql);
//            preStm.setNString(1, "%" + search + "%");
//            rs = preStm.executeQuery();
//            result = new ArrayList<>();
//            while (rs.next()) {
//                id = rs.getString("equipmentID");
//                name = rs.getNString("eqipmentName");
//                description = rs.getNString("equipmentDescription");
//                img = rs.getNString("equipmentImg");
//                amount = rs.getInt("equipmentAmount");
//                dto = new EquipmentDTO(id, name, description, img, amount);
//                result.add(dto);
//            }
//        } finally {
//            DBUtils.closeConnection(conn, preStm, rs);
//        }
//        return result;
//    }
    public List<EquipmentDTO> findByLikeName(String search, int start, int total) throws Exception {
        List<EquipmentDTO> result = null;
        String id, name, description, img;
        int amount;
        EquipmentDTO dto = null;
        try {
            String sql = "SELECT equipmentID, equipmentName, equipmentDescription, equipmentImg, equipmentAmount\n"
                    + "FROM dbo.tblEquipment\n"
                    + "WHERE equipmentName LIKE ? AND equipmentStatus=1\n"
                    + "ORDER BY equipmentName\n"
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
                id = rs.getString("equipmentID");
                name = rs.getNString("equipmentName");
                description = rs.getNString("equipmentDescription");
                img = rs.getNString("equipmentImg");
                amount = rs.getInt("equipmentAmount");
                dto = new EquipmentDTO(id, name, description, img, amount);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public int getAmountOfFindByLikeName(String search) throws Exception {
        int amount = 0;
        try {
            String sql = "SELECT COUNT(equipmentID) AS ResultCount\n"
                    + "FROM dbo.tblEquipment\n"
                    + "WHERE equipmentName LIKE ? AND equipmentStatus=1";
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

    public EquipmentDTO findByPrimaryKey(String id) throws Exception {
        EquipmentDTO dto = null;
        String name, description, img;
        int amount;
        try {
            String sql = "SELECT equipmentName, equipmentDescription, equipmentImg, equipmentAmount \n"
                    + "FROM dbo.tblEquipment\n"
                    + "WHERE equipmentID=? AND equipmentStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                name = rs.getNString("equipmentName");
                description = rs.getNString("equipmentDescription");
                img = rs.getNString("equipmentImg");
                amount = rs.getInt("equipmentAmount");
                dto = new EquipmentDTO(id, name, description, img, amount);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return dto;
    }

    public boolean updateEquipment(EquipmentDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE dbo.tblEquipment \n"
                    + "SET equipmentName=?, equipmentDescription=?, equipmentImg=?, equipmentAmount=?\n"
                    + "WHERE equipmentID=?";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, dto.getEquipmentName());
            preStm.setNString(2, dto.getEquipmentDescription());
            preStm.setString(3, dto.getEquipmentImage());
            preStm.setInt(4, dto.getEquipmentAmount());
            preStm.setString(5, dto.getEquipmentID());
            check = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }

    public void updateEquipmentAmount(HashMap<FateEquipmentDetailDTO, Integer> cart) throws Exception {
        try {
            String sql = "UPDATE dbo.tblEquipment \n"
                    + "SET equipmentAmount-=?\n"
                    + "WHERE equipmentID=? ";
            conn = DBUtils.makeConnection();
            conn.setAutoCommit(false);
            preStm = conn.prepareStatement(sql);
            for (Map.Entry<FateEquipmentDetailDTO, Integer> entry : cart.entrySet()) {
                preStm.setInt(1, entry.getValue());
                preStm.setString(2, entry.getKey().getEquipmentID());
                preStm.executeUpdate();
            }
            conn.commit();
        } finally {
            conn.setAutoCommit(true);
            DBUtils.closeConnection(conn, preStm, rs);
        }
    }

    public boolean deleteEquipment(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE dbo.tblEquipment "
                    + "SET equipmentStatus=0 "
                    + "WHERE equipmentID=?";
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
