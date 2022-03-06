<%-- 
    Document   : login
    Created on : Mar 4, 2022, 2:24:17 PM
    Author     : markhipz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %> 
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" placeholder="Username"></br>
            Password <input type="password" name="password" placeholder="Password"></br>
            <input type="submit" name="action" value="Login">
            <input type="reset" value="Reset">
        </form>
        <%=error%>
    </body>
</html>
