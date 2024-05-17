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
        <c:forEach var="freight" items="${transportationList}">
            <option value="${freight.id}">${freight.name}</option>
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


<h2>All Freight</h2>
<table border="1">
    <thead>
    <tr>
        <th>Route ID</th>
        <th>Route Name</th>
        <th>Transportation ID</th>
        <th>Transportation Name</th>
        <th>Weight</th>
        <th>ETA</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Failed</th>
        <th>Delivered</th>
        <th>Journey Started</th>
        <th>Start Journey</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${freightList}" var="freight">
        <tr>
            <td>${freight.routeId}</td>
            <td>${freight.routeName}</td>
            <td>${freight.transportationId}</td>
            <td>${freight.transportationName}</td>
            <td>${freight.weight}</td>
            <td>${freight.eta}</td>
            <td>${freight.startDate}</td>
            <td>${freight.endDate}</td>
            <td>${freight.failed}</td>
            <td>${freight.delivered}</td>
            <td>${freight.hasJourneyStarted}</td>
            <td>
                <form action="update-freight-journey-status" method="post">
                    <input type="text" hidden="hidden" name="has_journey_started" value=${freight.hasJourneyStarted}>
                    <input type="text" hidden="hidden" name="freight_id" value=${freight.id}>
                    <button type="submit">Mark Started</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
