<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 5/13/2024
  Time: 2:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<%--<%--%>
<%--    String successMessage = request.getAttribute("success_message").toString();--%>
<%--%>--%>
<body>
<h1>Error</h1>

<h1><c:out value="${error_message}"/></h1>
<a href="#" onclick="history.back(); return false;">Go back</a>

</body>
</html>
