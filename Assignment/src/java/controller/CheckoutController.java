/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import product.Product;
import product.ProductDAO;
import shopping.Cart;
import shopping.OrderDAO;
import user.User;

/**
 *
 * @author markhipz
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "MainController?action=Search&search=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            HttpSession session = request.getSession(false);
            String message = "";
            if (session == null) {
                message = "Session error!";
                request.setAttribute("MESSAGE", message);
            } else {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    message = "Cart not exist!";
                    request.setAttribute("MESSAGE", message);
                } else {
                    ProductDAO pDao = new ProductDAO();
                    for (Product p : cart.getCart().values()) {
                        Product product = pDao.getProduct(p.getProductID());
                        int remain = product.getQuantity();
                        if (p.getQuantity() > remain) {
                            message = "Not enought quantity, the remaining: " + remain;
                            request.setAttribute("MESSAGE", message);
                            break;
                        }
                    }
                    if (message.equals("")) {
                        OrderDAO oDao = new OrderDAO();
                        User user = (User) session.getAttribute("LOGIN_USER");
                        int total = Integer.parseInt(request.getParameter("total"));
                        oDao.checkOut(cart, user, total);
                        session.removeAttribute("CART");
                        message = "Checkout successfully";
                        request.setAttribute("MESSAGE", message);
                        url = SUCCESS;
                    }
                }
            }

        } catch (Exception e) {
            log("Error at CheckoutController" + e.toString());
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
