<%--
  Created by IntelliJ IDEA.
  User: Achintha
  Date: 4/9/2024
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Account</title>
</head>
<body>
<form action="register" method="post">
    <table>
        <tr>
            <th>Name</th>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <th>Email</th>
            <td>
                <input type="text" name="email">
            </td>
        </tr>
        <tr>
            <th>Password</th>
            <td>
                <input type="password" name="password">
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Register">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
