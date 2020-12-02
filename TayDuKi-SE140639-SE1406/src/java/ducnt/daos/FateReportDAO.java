/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.dtos.FateReportDTO;
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
public class FateReportDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public List<FateReportDTO> getHistoryFatesOfActor(String actorID, int start, int total) throws Exception {
        List<FateReportDTO> result = null;
        FateReportDTO dto = null;
        String fateID, roleName, roleDescription;
        Date startTime, endTime;
        String requirementFile;
        try {
            String sql = "SELECT tblFate.fateID, roleName, roleDescription, startTime, endTime, requirementFile\n"
                    + "FROM dbo.tblFate JOIN dbo.tblFateActorDetail ON tblFateActorDetail.fateID = tblFate.fateID\n"
                    + "WHERE actorID=? AND endTime < GETDATE() AND fateStatus=1\n"
                    + "ORDER BY tblFate.fateID\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY ";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actorID);
            preStm.setInt(2, start);
            preStm.setInt(3, total);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                fateID = rs.getString("fateID");
                roleName = rs.getNString("roleName");
                roleDescription = rs.getNString("roleDescription");
                startTime = rs.getDate("startTime");
                endTime = rs.getDate("endTime");
                requirementFile = rs.getNString("requirementFile");
                dto = new FateReportDTO(fateID, actorID, roleName, roleDescription, startTime, endTime, requirementFile);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public int getAmountHistoryFatesOfActor(String actorID) throws Exception {
        int count = 0;
        try {
            String sql = "SELECT COUNT(tblFate.fateID) AS ResultCount\n"
                    + "FROM dbo.tblFate JOIN dbo.tblFateActorDetail ON tblFateActorDetail.fateID = tblFate.fateID\n"
                    + "WHERE actorID=? AND endTime < GETDATE() AND fateStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actorID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("ResultCount");
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return count;
    }

    public List<FateReportDTO> getFollowingFatesOfActor(String actorID) throws Exception {
        List<FateReportDTO> result = null;
        FateReportDTO dto = null;
        String fateID, roleName, roleDescription;
        Date startTime, endTime;
        String requirementFile;
        try {
            String sql = "SELECT tblFate.fateID, roleName, roleDescription, startTime, endTime, requirementFile\n"
                    + "FROM dbo.tblFate JOIN dbo.tblFateActorDetail ON tblFateActorDetail.fateID = tblFate.fateID\n"
                    + "WHERE actorID=? AND startTime >= GETDATE() AND fateStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actorID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                fateID = rs.getString("fateID");
                roleName = rs.getNString("roleName");
                roleDescription = rs.getNString("roleDescription");
                startTime = rs.getDate("startTime");
                endTime = rs.getDate("endTime");
                requirementFile = rs.getNString("requirementFile");
                dto = new FateReportDTO(fateID, actorID, roleName, roleDescription, startTime, endTime, requirementFile);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public List<FateReportDTO> getFatesFileByActorID(String actorID) throws Exception {
        List<FateReportDTO> result = null;
        FateReportDTO dto = null;
        String fateID, requirementFile;
        try {
            String sql = "SELECT DISTINCT tblFate.fateID, requirementFile\n"
                    + "FROM dbo.tblFate JOIN dbo.tblFateActorDetail ON tblFateActorDetail.fateID = tblFate.fateID\n"
                    + "WHERE actorID=? AND fateStatus=1";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, actorID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                fateID = rs.getString("fateID");
                requirementFile = rs.getNString("requirementFile");
                dto = new FateReportDTO(fateID, actorID, null, null, null, null, requirementFile);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }
}
