<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/11/2024
  Time: 11:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Merchant Home</title>
</head>
<%
    if (session.getAttribute("login") == null) {
//        response.sendRedirect("/web/login-merchant");
        response.sendRedirect("/web/login");
    }
%>
<body>
<h1>Merchant Home</h1>
<h1>Hello, ${sessionScope.name}</h1>
<a href="/web/register-product">Register Product</a>
<br>
<a href="/web/make-order">Make Order</a>
<br>
<a href="/web/merchant-view-order-tracking">Track Order</a>
<br>
<a href="/web/logout">Log out</a>
</body>
</html>
