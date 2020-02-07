<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">

    <title>Add tariff plan</title>
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


<br>
<div class="container w-50">
    <form action="do" method="post">
        <input type="hidden" name="action" value="add_tariff">


        <div class="form-group row">
            <label for="titleLabel" class="col-sm-2 col-form-label col-form-label-lg">Title</label>
            <div class="col-sm-10">
                <input name="title" class="form-control form-control-lg" id="titleLabel"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Speed</label>
            <div class="col-sm-10">
                <input type="number" name="speed" class="form-control form-control-lg"
                       id="speedLabel"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Price</label>
            <div class="col-sm-10">
                <input type="number" name="price" step="0.01"
                       class="form-control form-control-lg"/>
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
</html>
