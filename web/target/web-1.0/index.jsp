<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login from index.jsp</title>
</head>
<body>
<h1>Login from index.jsp</h1>
<form action="login" method="post">
    <table>
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
                <input type="submit" value="Login">
            </td>
        </tr>
    </table>
</form>

<a href="register.jsp">Create New Account</a>

</body>
</html>
