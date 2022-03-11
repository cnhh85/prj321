/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import product.Category;
import utils.DBUtils;

/**
 *
 * @author markhipz
 */
public class Utility {

    private static final String CATEGORY = "select categoryID, categoryName from tblCategory";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String pattern = "^\\d{1,2}/\\d{1,2}/\\d{4}$";

    public Utility() {
    }

    public static Date handleParseDate(String inputDate) {
        try {
            sdf.setLenient(false);
            Date date = sdf.parse(inputDate);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isValidUsingDate(Date importDate, Date usingDate) {
        if (!usingDate.after(importDate)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidImportDate(Date importDate) {
        Date today = new Date();
        return importDate.after(today);
    }

    public static List<Category> getListCategory() throws SQLException {
        List<Category> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CATEGORY);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    list.add(new Category(categoryID, categoryName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }

    public static String getPattern() {
        return pattern;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

}
