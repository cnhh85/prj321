<%-- 
    Document   : admin
    Created on : Mar 4, 2022, 3:18:11 PM
    Author     : markhipz
--%>

<%@page import="product.Category"%>
<%@page import="utility.Utility"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="product.Product"%>
<%@page import="java.util.List"%>
<%@page import="user.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <style>
            img{max-width:300px;
                height: auto;}
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            List<Product> listProduct = (List<Product>) session.getAttribute("PRODUCT_LIST");
            Utility utility = new Utility();
            List<Category> listCategory = (List<Category>) session.getAttribute("CATEGORY_LIST");
        %>
        <h1>Welcome <%=user.getFullName()%> to Admin page</h1>
        <a href="addProduct.jsp">Add Product</a>
        <a href="MainController?action=Logout" >Logout</a>

        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <p><%= message%></p>

        <%
            }
            if (listProduct == null) {
        %>
        <h1>There are no product</h1>
        <%  } else {
        %>
        <table border="1">
            <thead>
                <tr>                   
                    <th>No.</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category ID</th>
                    <th>Import Date</th>
                    <th>Using Date</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%if (listProduct.size() > 0) {
                        int count = 1;
                        for (Product product : listProduct) {%>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td><%= product.getProductID()%></td>
                    <td>
                        <input type="text" name="productName" value="<%= product.getProductName()%>" />
                    </td>
                    <td>
                        <img src="<%= product.getImage()%>" /></br>
                        <input type="text" name="image" value="<%= product.getImage()%>" />
                    </td>
                    <td>
                        <input type="text" name="price" value="<%= product.getPrice()%>" />
                    </td>
                    <td>
                        <input type="text" name="quantity" value="<%= product.getQuantity()%>" />
                    </td>
                    <td>
                        <input type="text" name="categoryID" value="<%= product.getCategoryID()%>" />
                    </td>
                    <td>
                        <input type="text" name="importDate" value="<%= Utility.getSdf().format(product.getImportDate())%>" />
                    </td>
                    <td>
                        <input type="text" name="usingDate" value="<%= Utility.getSdf().format(product.getUsingDate())%>" />
                    </td>
                    <td>
                        <input type ="hidden" name="productID" value="<%=product.getProductID()%>" />
                        <input type="submit" name="action" value="Update" />
                    </td>
                    <td>
                        <a href="MainController?action=DeleteProduct&productID=<%=product.getProductID()%>">Delete</a>
                    </td>
                </tr>
            </form>
            <%}
                    }
                }%>
        </tbody>
    </table>

    <%
        if (listCategory == null) {
    %>
    <h1>There are no Category</h1>
    <%
    } else {
    %>
    <table border="1">
        <thead>
            <tr>                   
                <th>No.</th>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>Update category</th>
            </tr>
        </thead>
        <tbody>
            <%if (listCategory.size() > 0) {
                    int count = 1;
                    for (Category category : listCategory) {%>
        <form action="MainController">
            <tr>
                <td><%= count++%></td>
                <td><%= category.getCategoryID()%></td>
                <td>
                    <input type="text" name="categoryName" value="<%= category.getCategoryName()%>" />
                </td>
                <td>
                    <input type ="hidden" name="categoryID" value="<%=category.getCategoryID()%>" />
                    <input type="submit" name="action" value="UpdateCategory" />
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
