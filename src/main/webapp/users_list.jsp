<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.01.2020
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Tariff Plane</td>
    </tr>
    <c:forEach items="${users}" var="client">
    <tr>
        <td>${client.id}</td>
        <td>${client.name}</td>
        <td>${client.surname}</td>
        <td>${client.getTariffPlan().getTitle()}</td>
    </tr>
    </c:forEach>
</body>
</html>
