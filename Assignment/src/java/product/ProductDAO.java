/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import product.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author markhipz
 */
public class ProductDAO {

    private static final String SELECT = "select productID, productName, image, price, quantity, categoryID, importDate, usingDate from tblProduct";
    private static final String SELECT1 = "select productID, productName, image, price, quantity, categoryID, importDate, usingDate from tblProduct where productID=?";
    private static final String DELETE = "delete tblProduct where productID=?";
    private static final String UPDATE = "update tblProduct set productName=?, image=?, price=?, quantity=?, categoryID=?, importDate=?, usingDate=? where productID=?";
    private static final String INSERT = "insert into tblProduct(productID, productName, image, price, quantity, categoryID, importDate, usingDate) values(?,?,?,?,?,?,?,?)";
    private static final String SEARCH = "select productID, productName, image, price, quantity, categoryID, importDate, usingDate from tblProduct where productName like ?";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    //these methods is for admin only
    public List<Product> getListProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SELECT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    Date importDate = new Date(rs.getDate("importDate").getTime());
                    Date usingDate = new Date(rs.getDate("usingDate").getTime());
                    list.add(new Product(productID, productName, image, price, quantity, categoryID, importDate, usingDate));
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

    public boolean deleteProduct(String productID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE);
                stm.setString(1, productID);
                result = stm.executeUpdate() > 0;
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
        return result;
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE);
                stm.setString(1, product.getProductName());
                stm.setString(2, product.getImage());
                stm.setString(3, String.valueOf(product.getPrice()));
                stm.setString(4, String.valueOf(product.getQuantity()));
                stm.setString(5, product.getCategoryID());
                stm.setString(6, sdf.format(product.getImportDate()));
                stm.setString(7, sdf.format(product.getUsingDate()));
                stm.setString(8, product.getProductID());
                result = stm.executeUpdate() > 0;
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
        return result;
    }

    public boolean addProduct(Product product) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT);
                stm.setString(1, product.getProductID());
                stm.setString(2, product.getProductName());
                stm.setString(3, product.getImage());
                stm.setString(4, String.valueOf(product.getPrice()));
                stm.setString(5, String.valueOf(product.getQuantity()));
                stm.setString(6, product.getCategoryID());
                stm.setString(7, sdf.format(product.getImportDate()));
                stm.setString(8, sdf.format(product.getUsingDate()));
                result = stm.executeUpdate() > 0;
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
        return result;
    }

    //these methods is for user only
    public List<Product> searchProduct(String search) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    Date importDate = new Date(rs.getDate("importDate").getTime());
                    Date usingDate = new Date(rs.getDate("usingDate").getTime());
                    list.add(new Product(productID, productName, image, price, quantity, categoryID, importDate, usingDate));
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

    public Product getProduct(String productID) throws SQLException {
        Product result = null;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SELECT1);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    Date importDate = new Date(rs.getDate("importDate").getTime());
                    Date usingDate = new Date(rs.getDate("usingDate").getTime());
                    result = new Product(productID, productName, image, price, quantity, categoryID, importDate, usingDate);
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

        return result;
    }

    public boolean sellProduct(Product product) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        Product dtbProduct = getProduct(product.getProductID());
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE);
                stm.setString(1, product.getProductName());
                stm.setString(2, product.getImage());
                stm.setString(3, String.valueOf(product.getPrice()));
                stm.setString(4, String.valueOf(dtbProduct.getQuantity() - product.getQuantity()));
                stm.setString(5, product.getCategoryID());
                stm.setString(6, sdf.format(product.getImportDate()));
                stm.setString(7, sdf.format(product.getUsingDate()));
                stm.setString(8, product.getProductID());
                result = stm.executeUpdate() > 0;
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
        return result;
    }
}
