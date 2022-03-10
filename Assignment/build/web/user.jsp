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
        <title>GGShop</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Welcome <%=user.getFullName()%></h1>
        <a href="MainController?action=Logout" >Logout</a>
        <a href="viewCart.jsp">View Cart</a>
        <form name="search" action="MainController">
            Product ID: <input type="text" name="search" placeholder="Enter ProductID" />
            <input type="submit" name="action" value="Search" />
        </form>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {

        %>
        <p><%= message%></p>
        <%
            }
            List<Product> listProduct = (List<Product>) session.getAttribute("PRODUCT_LIST");
            Utility utility = new Utility();
        %>
        <% if (listProduct == null) {
        %>
        <h1>Our store close for now!</h1>
        <%  } else {
        %>
        <table border="1">
            <thead>
                <tr>                   
                    <th>No.</th>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category</th>
                    <th>Import Date</th>
                    <th>Using Date</th>
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
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
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <p><%= product.getProductName()%></p>
                    </td>
                    <td>
                        <img src="<%= product.getImage()%>" />
                    </td>
                    <td>
                        <p><%= product.getPrice()%></p>
                    </td>
                    <td>
                        <p><%= product.getQuantity()%></p>
                    </td>
                    <td>
                        <p>
                            <%= categoryName%></p>
                    </td>
                    <td>
                        <p><%= utility.getSdf().format(product.getImportDate())%></p>
                    </td>
                    <td>
                        <p><%= utility.getSdf().format(product.getUsingDate())%></p>
                    </td>
                    <td>
                        <input type ="hidden" name="productID" value="<%=product.getProductID()%>" />
                        <input type="submit" name="action" value="Add to cart" />

                    </td>
                </tr>
            </form>
            <%}
                    }
                }%>
        </tbody>
    </table>

</body>
</html>
