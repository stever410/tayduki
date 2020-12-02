/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.utils;

import java.io.Serializable;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author ngota
 */
public class DBUtils implements Serializable {

    public static Connection makeConnection() throws Exception {
        Context context = new InitialContext();
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("TAYDUKI");
        Connection conn = ds.getConnection();
        return conn;
    }

    public static void closeConnection(Connection cn, PreparedStatement preStm, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (cn != null) {
            cn.close();
        }
    }
}
