<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/13/2024
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Route</title>
</head>
<body>
<h2>Add Route</h2>
<form action="add-route" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>
    <label for="startingPoint">Starting Point:</label>
    <input type="text" id="startingPoint" name="startingPoint" required><br><br>
    <label for="destinationPoint">Destination Point:</label>
    <input type="text" id="destinationPoint" name="destinationPoint" required><br><br>
    <input type="submit" value="Register Route">
</form>
</body>
</html>
