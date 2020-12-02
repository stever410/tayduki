/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.dtos.EquipmentReportDTO;
import ducnt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ngota
 */
public class EquipmentReportDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public List<EquipmentReportDTO> findEquipmentsByDateAndStatus(Date startTime, Date endTime, boolean status, int start, int total) throws Exception {
        List<EquipmentReportDTO> result = null;
        String equipmentID, fateID, directorID;
        Date addDate;
        boolean equipmentStatus;
        EquipmentReportDTO dto = null;
        try {
            String sql = "SELECT tblEquipment.equipmentID, fateID, addDate, equipmentStatus, commiterID\n"
                    + "FROM dbo.tblEquipment JOIN dbo.tblFateEquipmentDetail ON tblFateEquipmentDetail.equipmentID = tblEquipment.equipmentID\n"
                    + "WHERE equipmentStatus=? AND (addDate BETWEEN ? AND ?)\n"
                    + "ORDER BY addDate\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, status);
            preStm.setDate(2, new java.sql.Date(startTime.getTime()));
            preStm.setDate(3, new java.sql.Date(endTime.getTime()));
            preStm.setInt(4, start);
            preStm.setInt(5, total);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                equipmentID = rs.getString("equipmentID");
                fateID = rs.getString("fateID");
                addDate = rs.getDate("addDate");
                equipmentStatus = rs.getBoolean("equipmentStatus");
                directorID = rs.getString("commiterID");
                dto = new EquipmentReportDTO(equipmentID, fateID, addDate, equipmentStatus, directorID);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public int getAmountOfFindEquipmentsByDateAndStatus(Date startTime, Date endTime, boolean status) throws Exception {
        int amount = 0;
        try {
            String sql = "SELECT COUNT(tblEquipment.equipmentID) AS ResultCount\n"
                    + "FROM dbo.tblEquipment JOIN dbo.tblFateEquipmentDetail ON tblFateEquipmentDetail.equipmentID = tblEquipment.equipmentID\n"
                    + "WHERE equipmentStatus=? AND (addDate BETWEEN ? AND ?)\n";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, status);
            preStm.setDate(2, new java.sql.Date(startTime.getTime()));
            preStm.setDate(3, new java.sql.Date(endTime.getTime()));
            rs = preStm.executeQuery();
            if (rs.next()) {
                amount = rs.getInt("ResultCount");
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return amount;
    }
}
