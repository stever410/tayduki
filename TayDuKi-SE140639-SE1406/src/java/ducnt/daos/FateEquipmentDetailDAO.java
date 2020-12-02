/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.daos;

import ducnt.dtos.FateEquipmentDetailDTO;
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
public class FateEquipmentDetailDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    public boolean insertFateEquipmentDetailCart(String fateID, String directorID, HashMap<FateEquipmentDetailDTO, Integer> cart) throws Exception {
        boolean check = true;
        try {
            String sql = "INSERT INTO dbo.tblFateEquipmentDetail(fateID, equipmentID, equipmentAmount, addDate, commiterID)\n"
                    + "VALUES (?,?,?,?,?)";
            conn = DBUtils.makeConnection();
            conn.setAutoCommit(false);
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, fateID);
            preStm.setDate(4, new java.sql.Date(new Date().getTime()));
            preStm.setString(5, directorID);
            for (Map.Entry<FateEquipmentDetailDTO, Integer> entry : cart.entrySet()) {
                preStm.setString(2, entry.getKey().getEquipmentID());
                preStm.setInt(3, entry.getValue());
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
