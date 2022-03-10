<%-- 
    Document   : viewCart
    Created on : Mar 10, 2022, 1:10:57 PM
    Author     : markhipz
--%>

<%@page import="product.Product"%>
<%@page import="shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>Welcome to the cart</h1>
        <a href="user.jsp">Return</a>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
        %>
        <p>There are nothing in cart</p>
        <%
        } else {
        %>
        <table border="1">
            <thead>
                <tr>                   
                    <th>No.</th>
                    <th>Image</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Update</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Product product : cart.getCart().values()) {
                %>
            <form action="MainController">
                <tr>
                    <td><%=count++%></td>  
                    <td><%=product.getImage()%></td>
                    <td><%=product.getProductName()%></td>
                    <td><%=product.getPrice()%></td>
                    <td><input type="number" name="quantity" value="<%=product.getQuantity()%>" </td>
                    <td><%=product.getPrice() * product.getQuantity()%></td>
                    <td>                        
                        <input type ="hidden" name="productID" value="<%=product.getProductID()%>" />
                        <input type="submit" name="action" value="UpdateCart" />
                    </td>
                    <td>
                        <a href="MainController?action=DeleteCart&productID=<%=product.getProductID()%>">Delete</a>
                    </td>
                </tr>
            </form>
            <%
                    }
                }
            %>
    </body>
</html>
