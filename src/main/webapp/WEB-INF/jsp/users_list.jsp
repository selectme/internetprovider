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
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">

    <title>Users</title>

</head>
<body class="container">



<div class="container">
    <nav class="navbar navbar-light">
        <form class="form-inline">

            <button type="button" class="btn bg-white">
                <button type="button" class="btn bg-white">
                    <form class="form-inline">
                        <input class="btn btn-light shadow" type="submit" value="Home">
                        <a href="/"></a>
                    </form>
                </button>

            <c:if test="${user != null}">
                <c:if test="${user.role == 'CLIENT'}">
                    <button type="button" class="btn bg-white">
                        <form class="form-inline" action="do" method="get">
                            <input type="hidden" name="action" value="show_client_account_page">
                            <input type="hidden" name="user_id" value="${user.id}">
                            <input class="btn btn-light shadow" type="submit" value="My account">
                        </form>
                    </button>
                </c:if>
                <c:if test="${user.role == 'ADMIN'}">
                    <button type="button" class="btn bg-white">
                    <form class="form-inline" action="do" method="get">
                        <input type="hidden" name="action" value="show_administration_panel">
                        <input class="btn btn-light shadow" type="submit" value="Administration panel">
                    </form>
                </c:if>
                </button>
            </c:if>
            <c:choose>
                <c:when test="${user == null}">
                    <button type="button" class="btn bg-white btn-right">
                        <form action="do" method="get">
                            <input type="hidden" name="action" value="show_login_page"/>
                            <input class="btn btn-light shadow" type="submit" value="Login">
                        </form>
                    </button>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn bg-white btn-left">
                        <form class="form-inline" action="do" method="get">
                            <input type="hidden" name="action" value="do_logout">
                            <input class="btn btn-light shadow" type="submit" value="Logout">
                        </form>
                    </button>
                </c:otherwise>
            </c:choose>
        </form>
    </nav>
</div>

<div class="container">
    <table class="table table-sm table-hover">
        <thead class="table-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Login</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Tariff Plan</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>${client.id}</td>
                <td>${client.login}</td>
                <td><c:out value="${client.name}"/></td>
                <td><c:out value="${client.surname}"/></td>
                <td><c:out value="${client.getTariffPlan().getTitle()}"/></td>
                <c:choose>
                    <c:when test="${client.status=='BLOCKED'}">
                        <td class="bg-danger">${client.status}</td>
                    </c:when>
                    <c:when test="${client.status=='INACTIVE'}">
                        <td class="bg-Warning">${client.status}</td>
                    </c:when>
                    <c:otherwise>
                        <td class="bg-success">${client.status}</td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_edit_user_page_by_admin"/>
                        <input type="hidden" name="user_id" value="${client.id}"/>
                        <input class="btn btn-light" type="submit" value="Edit"/>
                    </form>
                </td>
                <td>
                    <form action="do" method="post">
                        <input type="hidden" name="action" value="delete_user"/>
                        <input type="hidden" name="user_id" value="${client.id}"/>
                        <input class="btn btn-light btn" type="submit" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="do" method="get">
        <input type="hidden" name="action" value="show_add_client_page">
        <input class="btn btn-light" type="submit" value="Add client">
    </form>
</div>


<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>

</body>
</html>
