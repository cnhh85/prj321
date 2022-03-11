/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.Category;
import product.CategoryError;
import product.ProductDAO;
import utility.Utility;

/**
 *
 * @author markhipz
 */
@WebServlet(name = "AddCategoryController", urlPatterns = {"/AddCategoryController"})
public class AddCategoryController extends HttpServlet {

    private static final String ERROR = "addProduct.jsp";
    private static final String SUCCESS = "AdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            boolean check = true;
            CategoryError cE = new CategoryError();
            ProductDAO pDao = new ProductDAO();
            List<Category> listCategory = Utility.getListCategory();

            String categoryID = request.getParameter("categoryID");
            if (categoryID.length() > 10 || categoryID.length() < 2) {
                cE.setCategoryIDError("Category ID must be [2,10]");
                check = false;
            } else {
                for (Category c : listCategory) {
                    if (c.getCategoryID().equals(categoryID)) {
                        cE.setCategoryIDError("Category ID is duplicated!");
                        request.setAttribute("CATEGORY_ERROR", cE);
                        check = false;
                        break;
                    }
                }
            }
            String categoryName = request.getParameter("categoryName");
            if (categoryName.length() > 50 || categoryName.length() < 5) {
                cE.setCategoryNameError("Category name must be [5,50]");
                check = false;
            }

            if (check) {
                boolean success = false;
                Category category = new Category(categoryID, categoryName);
                success = pDao.addCategory(category);
                if (success) {
                    String message = "Add success fully " + categoryID;
                    request.setAttribute("MESSAGE", message);
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("CATEGORY_ERROR", cE);
            }
        } catch (SQLException e) {
            log("Error at AddCategoryController " + e.toString());
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
