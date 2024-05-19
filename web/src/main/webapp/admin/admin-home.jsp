<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/13/2024
  Time: 7:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    if (session.getAttribute("admin-login") == null) {
        response.sendRedirect("/web/login");
    }
%>
<body>
<h1>Admin Tasks</h1>
<a href="/web/register-transportation">Register Transportation</a>
<br>
<a href="/web/register-transportation-type">Register Transportation Type</a>
<br>
<a href="/web/add-destination">Add Destinations</a>
<br>
<a href="/web/freight-registration">Freight Registration</a>
<br>
<a href="/web/freight-tracking">Freight Tracking</a>
<br>
<a href="/web/logout">Log out</a>
</body>
</html>
