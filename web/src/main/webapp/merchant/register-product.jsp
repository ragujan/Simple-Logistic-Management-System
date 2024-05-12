
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Register Product</h2>
<form action="/web/register-product" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title"><br><br>

    <label for="weight">Weight:</label>
    <input type="text" id="weight" name="weight"><br><br>

    <label for="units">Units:</label>
    <select id="units" name="units">
        <option value="kg">Kilograms (kg)</option>
        <option value="g">Grams (g)</option>
        <option value="lb">Pounds (lb)</option>
        <option value="oz">Ounces (oz)</option>
    </select>


    <input type="submit" value="Register">
</form>
</body>
</html>
