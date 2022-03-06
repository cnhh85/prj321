<%-- 
    Document   : admin
    Created on : Mar 4, 2022, 3:18:11 PM
    Author     : markhipz
--%>

<%@page import="user.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Welcome <%=user.getFullName()%> to Admin page</h1>
        <a href="MainController?action=Logout" >Logout</a>
    </body>
</html>
