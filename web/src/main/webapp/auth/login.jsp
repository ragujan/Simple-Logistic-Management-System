<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/19/2024
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%

    if (session.getAttribute("admin-login") != null) {
        response.sendRedirect("/web/admin-home");
    }
%>
<body>
<h1>Login</h1>
<form action="login" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <label for="userType">User Type:</label>
    <select id="userType" name="userType" required>
        <option value="admin">Admin</option>
        <option value="merchant">Merchant</option>
    </select>
    <br>
    <button type="submit">Login</button>
</form>
</body>
</html>
