/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import utils.DBUtils;

/**
 *
 * @author markhipz
 */
public class UserDAO {

    private static final String LOGIN = "select fullName, roleID, address, birthday, phone, email, status from tblUsers where userID=? and password=?";

    public User checkLogin(String userID, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LOGIN);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String status = rs.getString("status");
                    user = new User(userID, fullName, "", roleID, address, birthday, phone, email, status);
                }
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

}
