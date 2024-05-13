<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/13/2024
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Transportation</title>
</head>
<body>
<h2>Add Transportation</h2>
<form action="register-transportation" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>
    <label for="transportationType">Transportation Type:</label>
    <select id="transportationType" name="transportationType" required>
        <c:forEach var="type" items="${transportationTypeList}">
            <option value="${type.name}">${type.name}</option>
        </c:forEach>
    </select><br><br>
    <input type="submit" value="Add Transportation">
</form>
</body>
</html>
