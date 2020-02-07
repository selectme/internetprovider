<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.01.2020
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
    <title>Title</title>
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

<%--<div class="container w-50">--%>
<%--<h2 align="">Editing tariff "${tariffPlan.title}"</h2>--%>
<%--</div>--%>

<br>
<div class="container w-50">
    <form action="do" method="post">
        <input type="hidden" name="action" value="edit_user_by_admin"/>

        <div class="form-group row">
            <label for="titleLabel" class="col-sm-2 col-form-label col-form-label-lg">ID</label>
            <div class="col-sm-10">
                <input name="user_id" value="${client.id}" class="form-control form-control-sm" id="colFormLabelSm"
                       placeholder="col-form-label-sm"
                       readonly="readonly">
            </div>
        </div>

        <div class="form-group row">
            <label for="titleLabel" class="col-sm-2 col-form-label col-form-label-lg">Login</label>
            <div class="col-sm-10">
                <input name="Login" value="${client.login}" class="form-control form-control-lg" id="titleLabel"
                       readonly="readonly"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Name</label>
            <div class="col-sm-10">
                <input type="text" name="name" value="${client.name}" class="form-control form-control-lg"
                       id="speedLabel"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Surname</label>
            <div class="col-sm-10">
                <input type="text" name="surname" value="${client.surname}"
                       class="form-control form-control-lg"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Tariff</label>
            <div class="col-sm-10">
                <select class="form-control form-control-lg" name="tariff_id">--%>
                    <c:forEach items="${tariffPlans}" var="tariffPlan">
                        <option value="${tariffPlan.id}">${tariffPlan.title}</option>
                        <option selected hidden value="${client.tariffPlan.id}">${client.tariffPlan.title}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Status</label>
            <div class="col-sm-10">
                <select class="form-control form-control-lg" name="status">
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="INACTIVE">INACTIVE</option>
                    <option value="BLOCKED">BLOCKED</option>
                    <option selected hidden value="${client.status}">${client.status}</option>
                </select>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button class="btn btn-default"><input type="submit" class="btn btn-success" value="Done"></button>
                </div>
            </div>
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>

<%--<html>--%>
<%--<head>--%>
<%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<c:if test="${user.role == 'ADMIN'}">--%>
<%--<form action="do" method="post">--%>
<%--<input type="hidden" name="action" value="edit_user_by_admin"/>--%>
<%--<table>--%>
<%--<tr>--%>
<%--<th>ID</th>--%>
<%--<th>Login</th>--%>
<%--<th>Name</th>--%>
<%--<th>Surname</th>--%>
<%--<th>Tariff</th>--%>
<%--<th>Status</th>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td><label><input type="number" name="user_id" value="${client.id}" readonly="readonly"/></label></td>--%>
<%--<td><label><input type="number" name="login" value="${client.login}" readonly="readonly"/></label></td>--%>
<%--<td><label><input type="text" name="name" value="${client.name}"/></label></td>--%>
<%--<td><label><input type="text" name="surname" value="${client.surname}"/></label></td>--%>
<%--<td><label>--%>
<%--<select name="tariff_id">--%>
<%--<c:forEach items="${tariffPlans}" var="tariffPlan">--%>
<%--<option value="${tariffPlan.id}">${tariffPlan.title}</option>--%>
<%--<option selected hidden value="${client.tariffPlan.id}">${client.tariffPlan.title}</option>--%>
<%--</c:forEach>--%>
<%--</select>--%>
<%--</label>--%>
<%--</td>--%>
<%--<td><label>--%>
<%--<select name="status">--%>
<%--<option value="ACTIVE">ACTIVE</option>--%>
<%--<option value="INACTIVE">INACTIVE</option>--%>
<%--<option value="BLOCKED">BLOCKED</option>--%>
<%--<option selected hidden value="${client.status}">${client.status}</option>--%>
<%--</select>--%>
<%--</label>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--<input type="submit" value="Done"/>--%>
<%--</form>--%>

<%--</c:if>--%>

</body>
</html>
