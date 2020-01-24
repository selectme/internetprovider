<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.01.2020
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add tariff plan</title>
</head>
<body>
<form action="/do" method="post">
    <input type="hidden" name="action" value="add_tariff">
    <label>Title:
        <input type="text" name="title"/>
    </label>
    <label>Speed:
        <input type="number" name="speed"/>
    </label>
    <label>Price:
        <input type="number" step="0.05" name="price"/>
    </label>
    <input type="submit" value="Add">
</form>
</body>
</html>
