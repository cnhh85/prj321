<%-- Document : login Created on : Mar 4, 2022, 2:24:17 PM Author : markhipz
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="./assets/login.css" />
    <title>Abyssoque</title>
  </head>
  <body>
    <% String error = (String) request.getAttribute("ERROR"); if (error ==
    null){ error = ""; }%>
    <div class="page">
      <div class="container">
        <div class="left">
          <div class="login">Login</div>
          <div class="eula">
            By logging in you agree to the ridiculously long terms that you
            didn't bother to read
          </div>
        </div>
        <div class="right">
          <div class="form">
            <form action="MainController" method="POST">
              <label for="text">User ID</label>
              <input type="text" name="userID" id="insert" />
              <label for="password">Password</label>
              <input type="password" name="password" id="insert" />
              <input type="submit" id="Login" name="action" value="Login" />
            </form>
            <h5 class="error-message"><%=error%></h3>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
