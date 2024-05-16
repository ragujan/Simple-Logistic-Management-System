<%@ page import="com.jiat.ejb.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Make Order</title>
</head>
<body>
<h2>Make Order</h2>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
    for (Product product : productList) {
        System.out.println("product from jsp page" + product.getTitle());
    }

%>
<form action="make-order" method="post">

    <label for="qty">QTY:</label>
    <input type="text" id="qty" name="qty"><br><br>

    <label for="expectedDate">Expected Date:</label>
    <input type="datetime-local" id="expectedDate" name="expectedDate"><br><br>

    <select id="product" name="product">
        <c:forEach items="${productList}" var="product">
            <option value="${product.title}">${product.title}</option>
        </c:forEach>
    </select>
    <select id="destinationId" name="destinationId">
        <c:forEach items="${destinationList}" var="destination">
            <option value="${destination.id}">${destination.destinationName}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Register">
</form>
</body>
</html>
