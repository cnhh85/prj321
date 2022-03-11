<%-- 
    Document   : user
    Created on : Mar 4, 2022, 3:19:06 PM
    Author     : markhipz
--%>

<%@page import="product.Category"%>
<%@page import="java.util.List"%>
<%@page import="utility.Utility"%>
<%@page import="product.Product"%> 
<%@page import="user.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/user.css" />
        <title>Abyssoque</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <div class="page">
            <div class="head-content">
                <h1>Welcome <%=user.getFullName()%> to Abyssoque!</h1>
                <a href="MainController?action=Logout" class="btn logout">Logout</a>
                <a href="viewCart.jsp" class="btn">View Cart</a>
            </div>
            <div class="search-box">
                <form name="search" action="MainController">
                    <input type="text" name="search" placeholder="Enter ProductID" class="search" />
                    <input type="submit" name="action" value="Search" class="search-btn"/>
                </form>
            </div>
                <%
                    String message = (String) request.getAttribute("MESSAGE");
                    if (message != null) {
                %>
                <p class="message"><%= message%></p>
            
            <%
                }
                List<Product> listProduct = (List<Product>) session.getAttribute("PRODUCT_LIST");
                Utility utility = new Utility();
            %>
            <% if (listProduct == null || listProduct.size() == 0) {
            %>
            <h1>There are no product!</h1>
            <%  } else {
            %>

            <div class="wrapper" >

                <div class="grid-container">

                    <%if (listProduct.size() > 0) {
                            int count = 1;
                            for (Product product : listProduct) {
                                String categoryName = "";
                                for (Category c : utility.getListCategory()) {
                                    if (product.getCategoryID().equals(c.getCategoryID())) {
                                        categoryName = c.getCategoryName();
                                    }
                                }
                    %>
                    <div class="card">
                        <div class="image-wrapper">
                            <img src="<%= product.getImage()%>" /></div>
                        <div class="card-content">
                            <span class="card-name"><%= product.getProductName()%></span>
                            <span class="card-price"><%= product.getPrice()%>đ</span>
                            <p class="category-name"><%= categoryName%></p>
                        </div>
                        <div>
                            <form action="MainController">
                                <input type ="hidden" name="productID" value="<%=product.getProductID()%>" />
                                <input type="submit" name="action" value="ADD TO CART" class="submit" />
                            </form>
                        </div>
                    </div>

                    <%
                            }
                        } %>
                </div>
                <%
                    }
                %>
            </div>


        </div>
    </body>
</html>
