<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/11/2024
  Time: 11:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Merchant Login</title>
</head>
<body>
 <h1>Merchant Login</h1>
 <form action="login-merchant" method="post">
     <table>
         <tr>
             <th>Username</th>
             <td>
                 <input type="text" name="name">
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
</body>
</html>
