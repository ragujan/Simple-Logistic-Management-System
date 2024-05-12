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
    List<Product> productList =(List<Product>) request.getAttribute("productList");
    for (Product product:productList){
        System.out.println("product from jsp page"+ product.getTitle());
    }

%>
<form action="/web/make-order" method="post">

    <label for="qty">QTY:</label>
    <input type="text" id="qty" name="qty"><br><br>

    <select id="products" name="products">
        <c:forEach items="${productList}" var="product">
            <option value="${product.id}">${product.title}</option>
        </c:forEach>
    </select>


    <input type="submit" value="Register">
</form>
</body>
</html>
