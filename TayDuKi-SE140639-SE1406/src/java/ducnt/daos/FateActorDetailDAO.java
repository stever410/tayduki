/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.dtos.FateActorDetailDTO;
import ducnt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ngota
 */
public class FateActorDetailDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public boolean insertFateActorDetailCart(String fateID, String directorID, HashMap<String, FateActorDetailDTO> cart) throws Exception {
        boolean check = true;
        try {
            String sql = "INSERT INTO dbo.tblFateActorDetail(fateID, actorID, roleName, roleDescription, addDate, commiterID)\n"
                    + "VALUES (?,?,?,?,?,?)";
            conn = DBUtils.makeConnection();
            conn.setAutoCommit(false);
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, fateID);
            preStm.setDate(5, new java.sql.Date(new Date().getTime()));
            preStm.setString(6, directorID);
            for (Map.Entry<String, FateActorDetailDTO> entry : cart.entrySet()) {
                preStm.setString(2, entry.getValue().getActorID());
                preStm.setNString(3, entry.getValue().getRoleName());
                preStm.setNString(4, entry.getValue().getRoleDescription());
                preStm.executeUpdate();
            }
            conn.commit();
            conn.setAutoCommit(true);
        } finally {
            DBUtils.closeConnection(conn, preStm, rs);
        }
        return check;
    }

}
