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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.css">

    <title>Users</title>

</head>
<body class="bg-light">
<button onclick="location.href='/'">Home</button>

<div class="container-fluid">
<table class="table table-striped table-responsive-xl">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Tariff Plan</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.getTariffPlan().getTitle()}</td>
            <td>${client.status}</td>
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
    </tbody>
</table>
</div>
<form action="do" method="get">
    <input type="hidden" name="action" value="show_add_client_page">
    <input type="submit" value="Add client">
</form>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
