/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.dtos.FateDTO;
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
public class FateDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public boolean addFate(FateDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO dbo.tblFate (fateID, fateName, fateDescription, shootPlace, startTime, endTime, shootCount, fateStatus, directorID, requirementFile)\n"
                    + "    VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getFateID());
            preStm.setNString(2, dto.getFateName());
            preStm.setNString(3, dto.getFateDescription());
            preStm.setNString(4, dto.getFateShootPlace());
            preStm.setDate(5, new java.sql.Date(dto.getFateStartTime().getTime()));
            preStm.setDate(6, new java.sql.Date(dto.getFateEndTime().getTime()));
            preStm.setInt(7, dto.getFateShootCount());
            preStm.setBoolean(8, dto.isActive());
            preStm.setString(9, dto.getFateDirectorID());
            preStm.setNString(10, dto.getFateRequirementFile());
            result = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public List<FateDTO> findByLikeName(String search, int start, int total) throws Exception {
        List<FateDTO> result = null;
        FateDTO dto = null;
        String fateID, fateName, fateDescription, fateShootPlace, fateDirectorID, fateRequirementFile;
        Date fateStartTime, fateEndTime;
        int shootCount;
        try {
            String sql = "SELECT fateID, fateName, fateDescription, shootPlace, startTime, endTime, shootCount, requirementFile, directorID\n"
                    + "FROM dbo.tblFate\n"
                    + "WHERE fateName LIKE ? AND fateStatus=1\n"
                    + "ORDER BY fateName\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setInt(2, start);
            preStm.setInt(3, total);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                fateID = rs.getString("fateID");
                fateName = rs.getNString("fateName");
                fateDescription = rs.getNString("fateDescription");
                fateShootPlace = rs.getNString("shootPlace");
                fateStartTime = rs.getDate("startTime");
                fateEndTime = rs.getDate("endTime");
                shootCount = rs.getInt("shootCount");
                fateRequirementFile = rs.getNString("requirementFile");
                fateDirectorID = rs.getString("directorID");
                dto = new FateDTO(fateID, fateName, fateDescription, fateShootPlace, fateStartTime, fateEndTime, shootCount, fateRequirementFile, fateDirectorID);
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
            String sql = "SELECT COUNT(fateID) AS ResultCount\n"
                    + "FROM dbo.tblFate\n"
                    + "WHERE fateName LIKE ? AND fateStatus=1";
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

    public FateDTO findByPrimaryKey(String id) throws Exception {
        FateDTO dto = null;
        String fateID, fateName, fateDescription, fateShootPlace, fateDirectorID, fateRequirementFile;
        Date fateStartTime, fateEndTime;
        int shootCount;
        try {
            String sql = "SELECT fateID, fateName, fateDescription, shootPlace, startTime, endTime, shootCount, requirementFile, directorID \n"
                    + "FROM dbo.tblFate\n"
                    + "WHERE fateID=? AND fateStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                fateID = rs.getString("fateID");
                fateName = rs.getNString("fateName");
                fateDescription = rs.getNString("fateDescription");
                fateShootPlace = rs.getNString("shootPlace");
                fateStartTime = rs.getDate("startTime");
                fateEndTime = rs.getDate("endTime");
                shootCount = rs.getInt("shootCount");
                fateRequirementFile = rs.getNString("requirementFile");
                fateDirectorID = rs.getString("directorID");
                dto = new FateDTO(fateID, fateName, fateDescription, fateShootPlace, fateStartTime, fateEndTime, shootCount, fateRequirementFile, fateDirectorID);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return dto;
    }

    public boolean updateFate(FateDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE dbo.tblFate \n"
                    + "SET fateName=?, fateDescription=?, shootPlace=?, startTime=?, endTime=?, shootCount=?, directorID=?, requirementFile=?\n"
                    + "WHERE fateID=?";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, dto.getFateName());
            preStm.setNString(2, dto.getFateDescription());
            preStm.setNString(3, dto.getFateShootPlace());
            preStm.setDate(4, new java.sql.Date(dto.getFateStartTime().getTime()));
            preStm.setDate(5, new java.sql.Date(dto.getFateEndTime().getTime()));
            preStm.setInt(6, dto.getFateShootCount());
            preStm.setString(7, dto.getFateDirectorID());
            preStm.setNString(8, dto.getFateRequirementFile());
            preStm.setString(9, dto.getFateID());
            check = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }

    public boolean deleteFate(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE dbo.tblFate "
                    + "SET fateStatus=0 "
                    + "WHERE fateID=?";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }

    public List<FateDTO> findFateOfDirector(String directorID) throws Exception {
        FateDTO dto = null;
        String fateID, fateName, fateDescription, fateShootPlace, fateDirectorID, fateRequirementFile;
        Date fateStartTime, fateEndTime;
        int shootCount;
        List<FateDTO> result = null;
        try {
            String sql = "SELECT fateID, fateName, fateDescription, shootPlace, startTime, endTime, shootCount, requirementFile, directorID \n"
                    + "FROM dbo.tblFate\n"
                    + "WHERE directorID=? AND fateStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, directorID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                fateID = rs.getString("fateID");
                fateName = rs.getNString("fateName");
                fateDescription = rs.getNString("fateDescription");
                fateShootPlace = rs.getNString("shootPlace");
                fateStartTime = rs.getDate("startTime");
                fateEndTime = rs.getDate("endTime");
                shootCount = rs.getInt("shootCount");
                fateRequirementFile = rs.getNString("requirementFile");
                fateDirectorID = rs.getString("directorID");
                dto = new FateDTO(fateID, fateName, fateDescription, fateShootPlace, fateStartTime, fateEndTime, shootCount, fateRequirementFile, fateDirectorID);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }
}
