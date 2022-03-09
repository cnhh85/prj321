<%-- 
    Document   : addProduct
    Created on : Mar 8, 2022, 3:53:40 PM
    Author     : markhipz
--%>

<%@page import="user.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product Page</title>
    </head>
    <body>
        <%
            ProductError pE = (ProductError) request.getAttribute("PRODUCT_ERROR");
            pE = pE != null ? pE : new ProductError();
        %>
        <h1>Add product here:</h1>
        <a href="admin.jsp">Cancel</a>
        <form action="MainController" method="POST">
            Product ID <input type="text" name="productID" placeholder="Enter product ID" required="" />
            <%
                if (pE.getProductIDError() != "") {
            %>
            <span><%=pE.getProductIDError()%></span>
            <%
                }
            %>
            </br>
            Product Name <input type="text" name="productName" placeholder="Enter product name" required=""/>
            <%
                if (pE.getProductNameError() != "") {
            %>
            <span><%=pE.getProductNameError()%></span>
            <%
                }
            %>
            </br>
            Image <input type="text" name="image" placeholder="Enter image url" required="" />
            <%
                if (pE.getImageError() != "") {
            %>
            <span><%=pE.getImageError()%></span>
            <%
                }
            %>
            </br>
            Price <input type="text" name="price" placeholder="Enter price" required="" />
            <%
                if (pE.getPriceError() != "") {
            %>
            <span><%=pE.getPriceError()%></span>
            <%
                }
            %>
            </br>
            Quantity <input type="text" name="quantity" placeholder="Enter quantity" required="" />
            <%
                if (pE.getQuantityError() != "") {
            %>
            <span><%=pE.getQuantityError()%></span>
            <%
                }
            %>
            </br>
            Category ID <input type="text" name="categoryID" placeholder="Enter category ID" required="" />
            <%
                if (pE.getCategoryIDError() != "") {
            %>
            <span><%=pE.getCategoryIDError()%></span>
            <%
                }
            %>
            </br>
            Import Date <input type="text" name="importDate" placeholder="Enter import date (dd/mm/yyyy)" required="" />
            <%
                if (pE.getImportDateError() != "") {
            %>
            <span><%=pE.getImportDateError()%></span>
            <%
                }
            %>
            </br>
            Using Date <input type="text" name="usingDate" placeholder="Enter using date (dd/mm/yyyy)" required="" />
            <%
                if (pE.getUsingDateError() != "") {
            %>
            <span><%=pE.getUsingDateError()%></span>
            <%
                }
            %>
            </br>
            <input type="submit" name="action" value="AddProduct" />
            <input type="reset" value="Reset" />
            <%
                if (pE.getUnknownError() != "") {
            %>
            <span><%=pE.getUnknownError() %></span>
            <%
                }
            %>
        </form>
    </body>
</html>
