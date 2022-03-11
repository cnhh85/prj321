/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import product.Product;
import user.User;
import utils.DBUtils;

/**
 *
 * @author markhipz
 */
public class OrderDAO {
    
    private static final String INSERT_ORDER = "insert into tblOrder(orderID, orderDate, total, userID) values(?,?,?,?)";
    private static final String INSERT_ORDER_DETAIL = "insert into tblOrderDetail(detailID, price, quantity, orderID, productID) values(?,?,?,?,?)";
    private static final String COUNT = "select count(*) as result from tblOrder";
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    
    public boolean checkOut(Cart cart, User user, int total) throws SQLException {        
        String orderID = "oNo" + (count() + 1);
        Date now = new Date();
        Order order = new Order(orderID, now, total, user.getUserID());
        insertOrder(order);
        
        int count = 1;
        for (Product product : cart.getCart().values()) {
            String detailID = orderID + "_d" + count;
            OrderDetail oDtl = new OrderDetail(detailID, product.getPrice(), product.getQuantity(), orderID, product.getProductID());
            insertOrderDetail(oDtl);
        }
        
        return true;
    }
    
    private int count() throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(COUNT);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("result");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }
    
    private void insertOrder(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_ORDER);
                stm.setString(1, order.getOrderID());
                stm.setString(2, sdf.format(order.getOrderDate()));
                stm.setString(3, String.valueOf(order.getTotal()));
                stm.setString(4, order.getUserID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    private void insertOrderDetail(OrderDetail oDtl) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_ORDER_DETAIL);
                stm.setString(1, oDtl.getDetailID());
                stm.setString(2, String.valueOf(oDtl.getPrice()));
                stm.setString(3, String.valueOf(oDtl.getQuantity()));
                stm.setString(4, oDtl.getOrderID());
                stm.setString(4, oDtl.getProductID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
