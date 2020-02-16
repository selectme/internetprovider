<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:bundle basename="messages"/>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">
    <title>Hello</title>

</head>

<body class="container">

<div class="col-5">
    <li><a href="?sessionLocale=en">EN</a></li>
    <li><a href="?sessionLocale=ru">RU</a></li>
</div>

<div class="col border-bottom"><fmt:message key="welcome"/> ${user.name} ${user.surname} !</div>

<div class="container">
    <nav class="navbar navbar-light">

        <form class="form-inline">
            <form class="form-inline">
                <input class="btn btn-light shadow" type="submit" value="Home"/>
            </form>

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

<div class="jumbotron">
    <c:choose>
        <c:when test="${user != null}">
            <h1 class="display-4 ">Hello, ${user.name} ${user.surname}!</h1>
        </c:when>
        <c:otherwise>
            <h1 class="display-4">Hello!</h1>
        </c:otherwise>
    </c:choose>
    <p class="lead">Our internet provider welcomes you!</p>
    <hr class="my-4">
    <p>To view our tariff plans, click the button below</p>
    <a>
        <form action="do" method="get">
            <input type="hidden" name="action" value="show_tariffs">
            <input class="btn btn-primary btn-lg shadow-lg p-3" type="submit" value="Tariff plans">
        </form>
    </a>
</div>


<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>

</body>
</html>