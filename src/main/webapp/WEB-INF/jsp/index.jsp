<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" session="true" %>
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

<c:import url="/WEB-INF/jsp/header.jsp"/>
<h2>
    <fmt:message key="label.chooseSessionLocale" />
</h2>

<div class="col-5">
    <li><a href="?sessionLocale=en">EN</a></li>
    <li><a href="?sessionLocale=ru">RU</a></li>
</div>

<div class="col border-bottom"><fmt:message key="label.welcome"/><c:out value="${user.name}"/> <c:out value="${user.surname}"/> !
</div>


<div class="jumbotron">
    <c:choose>
        <c:when test="${user != null}">
            <h1 class="display-4 ">Hello, <c:out value="${user.name}"/> <c:out value="${user.surname}"/>!</h1>
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
<c:import url="/WEB-INF/jsp/footer.jsp"/>
</html>