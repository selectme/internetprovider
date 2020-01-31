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
<button onclick="location.href='/'">Home</button>
<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Tariff Plane</td>
    </tr>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.getTariffPlan().getTitle()}</td>
            <td>
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_edit_user_page_by_admin"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <td>
                <form action="do" method="post">
                    <input type="hidden" name="action" value="delete_user"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    <br>
</table>
<form action="do" method="get">
    <input type="hidden" name="action" value="show_add_client_page">
    <input type="submit" value="Add client">
</form>
</body>
</html>
