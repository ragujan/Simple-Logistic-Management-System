<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/18/2024
  Time: 7:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>
<head>
    <title>Freight Tracking Data</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2>Freight Tracking Data</h2>

<table>
    <thead>
    <tr>
        <th>Freight Tracking ID</th>
        <th>Freight ID</th>
        <th>Freight Route</th>
        <th>Route Destination</th>
        <th>Freight Progress</th>
        <th>Coordinates</th>
        <th>Expected Delay</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="model" items="${trackingDataModels}">
        <tr>
            <td>${model.freightTrackingId}</td>
            <td>${model.freightId}</td>
            <td>${model.freightRoute}</td>
            <td>${model.routeDestination}</td>
            <td>${model.freightProgress}</td>
            <td>${model.coordinates}</td>
            <td>${model.expectedDelay}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>