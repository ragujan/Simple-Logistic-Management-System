<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Freight Registration</title>
</head>
<body>
<h1>Freight Registration Form</h1>
<form action="freight-registration" method="post">
    <label for="transportation">Select Transportation:</label>
    <select name="transportation" id="transportation">
        <c:forEach var="transportation" items="${transportationList}">
            <option value="${transportation.id}">${transportation.name}</option>
        </c:forEach>
    </select>
    <br>
    <label for="route">Select Route:</label>
    <select name="route" id="route">
        <c:forEach var="route" items="${routeList}">
            <option value="${route.id}">${route.name}</option>
        </c:forEach>
    </select>
    <br>
    <label for="weight">Weight:</label>
    <input type="text" id="weight" name="weight">
    <br>
    <label for="startDate">Start Date:</label>
    <input type="datetime-local" id="startDate" name="startDate">
    <br>
    <label for="endDate">End Date:</label>
    <input type="datetime-local" id="endDate" name="endDate">
    <br>
    <label for="eta">ETA:</label>
    <input type="text" id="eta" name="eta">
    <br>
    <!-- Other form fields -->
    <input type="submit" value="Submit">
</form>
</body>
</html>
