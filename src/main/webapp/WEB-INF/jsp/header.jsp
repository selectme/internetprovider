<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">
    <title>Hello</title>
</head>


<body class="container">
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">


        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link font-weight-bold" href="/">IP</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>

            <c:if test="${user != null}">
                <c:if test="${user.role == 'CLIENT'}">
                    <li class="nav-item active">
                        <a class="nav-link" href="do?action=show_client_account_page&user_id=${user.id}">My
                            account</a>
                    </li>
                </c:if>
                <c:if test="${user.role == 'ADMIN'}">
                    <li class="nav-item active">
                        <a class="nav-link" href="do?action=show_administration_panel">Administration panel</a>
                    </li>
                </c:if>
            </c:if>


        </ul>

        <%--<div class="dropdown">--%>
        <%--<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
        <%--Dropdown button--%>
        <%--</button>--%>
        <%--<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">--%>
        <%--<a class="dropdown-item" href="?sessionLocale=ru">Русский</a>--%>
        <%--<a class="dropdown-item" href="?sessionLocale=by">Беларуски</a>--%>
        <%--<a class="dropdown-item" href="?sessionLocale=en">English</a>--%>
        <%--</div>--%>
        <%--</div>--%>

        <div class="btn-group">
            <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Action
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Separated link</a>
            </div>
        </div>

        <div>

                <c:choose>
                <c:when test="${sessionScope.lang=='ru'}">
                    <a href="?sessionLocale=ru">RU</a>
                </c:when>
                <c:when test="${sessionScope.lang=='by'}">
                    <a href="?sessionLocale=by">BY</a>
                </c:when>
                <c:otherwise>
                    <a href="?sessionLocale=en">EN</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${user == null}">
                    <a href="do?action=show_login_page">
                        <button type="button" class="btn btn-primary">Login</button>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="do?action=do_logout">
                        <button type="button" class="btn btn-primary">Logout</button>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</div>