<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Viktar
  Date: 04.02.2020
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">

    <title>Title</title>
</head>
<body class="container">

<div class="container">
    <nav class="navbar navbar-light">
        <form class="form-inline">
            <button type="submit" class="btn bg-white">
                <form class="form-inline">
                    <input class="btn btn-light shadow" type="submit" value="Home"/>
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

<div class="row">
    <div class="col-sm-6">
        <div class="card bg-light">
            <div class="card-body">
                <h5 class="card-title">Clients</h5>
                <p class="card-text">Аdding, editing, removing clients</p>
                <a>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_users">
                        <input class="btn btn-primary shadow-lg" type="submit" value="Edit">
                    </form>
                </a>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="card bg-light">
            <div class="card-body">
                <h5 class="card-title">Tariff plans</h5>
                <p class="card-text">Аdding, editing, removing tariff plans</p>
                <a>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_tariffs">
                        <input class="btn btn-primary shadow-lg" type="submit" value="Edit">
                    </form>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
