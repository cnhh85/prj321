<%-- Document : viewCart Created on : Mar 10, 2022, 1:10:57 PM Author : markhipz
--%> <%@page import="product.Category"%> <%@page import="utility.Utility"%>
<%@page import="product.Product"%> <%@page import="shopping.Cart"%> <%@page
contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Abyssoque</title>
    <link rel="stylesheet" href="./assets/viewCart.css" />
  </head>
  <body>
    <div class="page">
      <div class="top">
        <h1>Your shopping cart</h1>
        <a href="user.jsp" class="btn">Return</a>
      </div>
      <% int total = 0; Cart cart = (Cart) session.getAttribute("CART"); Utility
      utility = new Utility(); if (cart == null || cart.getCart().size() == 0) {
      %>
      <p>There are nothing in the cart</p>
      <% } else { %>
      <div class="wrapper">
        <div class="left">
          <table>
            <thead>
              <tr>
                <th scope="col">Product</th>
                <th scope="col" width="120">Quantity</th>
                <th scope="col" width="120">Price</th>
                <th scope="col" width="200"></th>
              </tr>
            </thead>
            <tbody>
              <% for (Product product : cart.getCart().values()) { String
              categoryName = ""; for (Category c : Utility.getListCategory()) {
              if (product.getCategoryID().equals(c.getCategoryID())) {
              categoryName = c.getCategoryName(); }} total += product.getPrice()
              * product.getQuantity();%>
              <form action="MainController">
                <tr>
                  <td>
                    <div class="col1">
                      <div class="img-wrapper">
                        <img src="<%= product.getImage()%>" />
                      </div>
                      <div class="info">
                        <h4 class="p-name"><%=product.getProductName()%></h4>
                        <p class="ctg"><%=categoryName%></p>
                      </div>
                    </div>
                  </td>
                  <td>
                    <input
                      type="number"
                      name="quantity"
                      value="<%=product.getQuantity()%>"
                      class="quantity"
                    />
                  </td>
                  <td>
                    <div class="price">
                      <h4 class="total">
                        <%=product.getPrice() * product.getQuantity()%>đ
                      </h4>
                      <p class="price-1"><%=product.getPrice()%>đ each</p>
                    </div>
                  </td>
                  <td>
                    <div class="action">
                      <input
                        type="hidden"
                        name="productID"
                        value="<%=product.getProductID()%>"
                      />
                      <input
                        type="submit"
                        name="action"
                        value="UpdateCart"
                        class="update"
                      />
                      <a
                        href="MainController?action=DeleteCart&productID=<%=product.getProductID()%>"
                        class="delete"
                        >Delete</a
                      >
                    </div>
                  </td>
                </tr>
              </form>
              <% }%>
            </tbody>
          </table>
        </div>
        <div class="right">
          <h1>Total: <%=total%>đ</h1>
          <div class="flex">
            <form action="MainController">
              <input type="hidden" name="total" value="<%=total%>" />
              <input
                type="submit"
                name="action"
                value="Checkout"
                class="submit"
              />
            </form>
          </div>
          <% String message = (String) request.getAttribute("MESSAGE"); if
          (message != null) {%>
          <p class="error"><%= message%></p>
          <% } } %>
        </div>
      </div>
    </div>
  </body>
</html>
