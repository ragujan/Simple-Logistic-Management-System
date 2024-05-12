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
<body>
<h1>Success</h1>

<c:set var="successMessage" value="${requestScope.success_message}" />

<c:if test="${not empty successMessage}">
    <h1>${successMessage}</h1>
</c:if>
<a href="/web/merchant/merchant-home.jsp">Home Page</a>
</body>
</html>
