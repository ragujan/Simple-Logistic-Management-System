<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<%
//    if (session.getAttribute("login") == null) {
//        response.sendRedirect("index.jsp");
//    }
%>
<body>
<h1>Hello, ${sessionScope.email}</h1>
<form action="transfer" method="post">
    <table>
        <tr>
            <th>Source Account No</th>
            <td><input type="text" name="sourceAccountNo"></td>
        </tr>
        <tr>
            <th>Destination Account No</th>
            <td><input type="text" name="destinationAccountNo"></td>
        </tr>
        <tr>
            <th>Amount</th>
            <td><input type="text" name="amount"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Transfer"></td>
        </tr>
    </table>
</form>


<h1>Schedule Task</h1>
<form action="schedule" method="post">
    <table>
        <tr>
            <th>Duration</th>
            <td><input type="text" name="duration"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Schedule"></td>
        </tr>
    </table>
</form>

<a href="logout">Logout</a>
</body>
</html>
