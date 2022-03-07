<%-- 
    Document   : user
    Created on : Mar 4, 2022, 3:19:06 PM
    Author     : markhipz
--%>

<%@page import="user.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KEYBOARD</title>
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
        <h1>Buy your own keyboard</h1>
        <a href="MainController?action=Logout" >Logout</a>
    </body>
</html>
