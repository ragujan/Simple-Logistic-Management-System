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
    <label for="name">Maximum Weight:</label>
    <input type="text" id="maximumWeight" name="maximumWeight" required><br><br>
    <label for="transportationType">Transportation Type:</label>
    <select id="transportationType" name="transportationType" required>
        <c:forEach var="type" items="${transportationTypeList}">
            <option value="${type.name}">${type.name}</option>
        </c:forEach>
    </select><br><br>
    <input type="submit" value="Add Transportation">
</form>

<h2>All Transportations</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Type</th>
        <th>Name</th>
        <th>Maximum Weight</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="transportation" items="${transportations}">
        <tr>
            <td>${transportation.id}</td>
            <td>${transportation.type}</td>
            <td>${transportation.name}</td>
            <td>${transportation.maximumWeight}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
