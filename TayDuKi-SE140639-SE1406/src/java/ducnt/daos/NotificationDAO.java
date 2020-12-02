/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.dtos.NotificationDTO;
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
public class NotificationDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public List<NotificationDTO> getTop10NotificationByUserID(String userID) throws Exception {
        List<NotificationDTO> result = null;
        NotificationDTO dto = null;
        String notificationDescription;
        String commiterID;
        Date createdDate;
        try {
            String sql = "SELECT TOP 10 notificationDesc, notificationStatus, commiterID, createdTime\n"
                    + "FROM dbo.tblNotification\n"
                    + "WHERE userID = ?\n"
                    + "ORDER BY createdTime DESC";
            conn = DBUtils.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                notificationDescription = rs.getNString("notificationDesc");
                commiterID = rs.getString("commiterID");
                createdDate = rs.getTimestamp("createdTime");
                dto = new NotificationDTO(userID, notificationDescription, commiterID, createdDate);
                result.add(dto);
            }
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return result;
    }
}
