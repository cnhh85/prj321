/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.Product;
import user.ProductDAO;
import user.ProductError;
import user.Utility;

/**
 *
 * @author markhipz
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {

    private static final String ERROR = "addProduct.jsp";
    private static final String SUCCESS = "AdminController";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductDAO pDao = new ProductDAO();
        Utility utility = new Utility();
        ProductError pE = new ProductError();
        boolean isValidated = true;
        boolean check = false;
        try {
            HttpSession session = request.getSession();
            List<Product> listProduct = pDao.getListProduct();
            session.setAttribute("PRODUCT_LIST", listProduct);

            //Handle ProductID Error
            String productID = request.getParameter("productID");
            if (productID.length() > 10 || productID.length() < 2) {
                pE.setProductIDError("Product ID must be [2,10]");
                isValidated = false;
            }

            //Handle ProductName Error
            String productName = request.getParameter("productName");
            if (productName.length() > 50 || productName.length() < 5) {
                pE.setProductNameError("Product name must be [5,50]");
                isValidated = false;
            }

            String image = request.getParameter("image");

            //Handle price Error
            int price;
            try {
                price = Integer.parseInt(request.getParameter("price"));
                if (price < 0) {
                    pE.setPriceError("Price can't smaller than 0");
                    isValidated = false;
                }
            } catch (NumberFormatException e) {
                log("Error parse int " + e.toString());
                pE.setPriceError("Price must be a number");
                isValidated = false;
                price = -1;
            }

            //Handle quantity Error
            int quantity;
            try {
                quantity = Integer.parseInt(request.getParameter("quantity"));
                if (quantity < 0) {
                    pE.setQuantityError("Quantity can't smaller than 0");
                    isValidated = false;
                }
            } catch (NumberFormatException e) {
                log("Error parse int " + e.toString());
                pE.setQuantityError("Quantity must be a number");
                isValidated = false;
                quantity = -1;
            }

            //Handle Category ID Error
            String categoryID = request.getParameter("categoryID");
            if (categoryID.length() > 5 || productID.length() < 2) {
                pE.setCategoryIDError("Product ID must be [2,5]");
                isValidated = false;
            }

            //Handle import date Error
            String importDate = request.getParameter("importDate");
            if (!importDate.matches(utility.getPattern())) {
                pE.setImportDateError("Invalid date format!");
                isValidated = false;
            } else if (utility.handleParseDate(importDate) == null) {
                pE.setImportDateError("Date not exist");
                isValidated = false;
            }

            //Handle using date Error
            String usingDate = request.getParameter("usingDate");
            if (!usingDate.matches(utility.getPattern())) {
                pE.setUsingDateError("Invalid date format!");
                isValidated = false;
            } else if (utility.handleParseDate(usingDate) == null) {
                pE.setUsingDateError("Date not exist");
                isValidated = false;
            } else if (!utility.isValidUsingDate(sdf.parse(importDate), sdf.parse(usingDate))) {
                pE.setUsingDateError("Using date must after Import date");
                isValidated = false;
            }

            //Handle duplicate product id Error
            if (isValidated) {
                for (Product product : listProduct) {
                    if (product.getProductID().equals(productID)) {
                        pE.setProductIDError("Product ID is duplicated: " + productID);
                        isValidated = false;
                    }
                }
            }
            if (isValidated) {
                check = pDao.addProduct(new Product(productID, productName, image, price, quantity, categoryID, sdf.parse(importDate), sdf.parse(usingDate)));
                if (check) {
                    url = SUCCESS;
                    request.setAttribute("CONFIRM_ADD", "Success fully add");
                } else {
                    //Handle unknown Error
                    pE.setUnknownError("Something went wrong");
                    request.setAttribute("PRODUCT_ERROR", pE);
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", pE);
            }
        } catch (Exception e) {
            log("Error at AddProductController " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
