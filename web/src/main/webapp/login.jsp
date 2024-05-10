
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login from login.jsp</title>
</head>
<body>
    <h1>Login from login.jsp</h1>
    <form action="j_security_check" method="post">
        <table>
            <tr>
                <th>Username</th>
                <td>
                    <input type="text" name="j_username">
                </td>
            </tr>
            <tr>
                <th>Password</th>
                <td>
                    <input type="password" name="j_password">
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Login">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
