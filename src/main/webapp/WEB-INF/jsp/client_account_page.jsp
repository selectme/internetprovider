<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">

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

<br>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-2">
            <div class="btn-group-vertical">
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_edit_client_by_client_page"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input type="submit" class="btn btn-light btn-lg text-right" value="Edit data"/>
                </form>
                <br>
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_change_tariff_page"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input type="submit" class="btn btn-light btn-lg" value="Change tariff"/>
                </form>
                <br>
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_payment_page"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input type="submit" class="btn btn-light btn-lg" value="Make payment"/>
                </form>
                <br>
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_clients_payments_page"/>
                    <input type="submit" class="btn btn-light btn-lg" value="Payment history"/>
                </form>
            </div>
        </div>
        <div class="col-md-auto">
            <div class="card col-auto" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">My personal page</h5>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Login: ${client.login}</li>
                    <li class="list-group-item">Name: ${client.name} ${client.surname}</li>
                    <li class="list-group-item">Tarff: ${client.getTariffPlan().getTitle()}</li>
                    <li class="list-group-item">Balance: ${client.moneyOnAccount}</li>
                    <li class="list-group-item">Status: ${client.status}</li>
                </ul>
            </div>
        </div>
        <div class="col-lg-2">
        </div>
    </div>
</div>


<%--<table border="2">--%>
<%--<tr>--%>
<%--<td>Login</td>--%>
<%--<td>Name</td>--%>
<%--<td>Surname</td>--%>
<%--<td>Tariff Plane</td>--%>
<%--<td>Money balance</td>--%>
<%--<td>Status</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>${client.getLogin()}</td>--%>
<%--<td>${client.name}</td>--%>
<%--<td>${client.surname}</td>--%>
<%--<td>${client.getTariffPlan().getTitle()}</td>--%>
<%--<td>${client.moneyOnAccount}</td>--%>
<%--<td>${client.status}</td>--%>
<%--</tr>--%>
<%--<br>--%>
<%--</table>--%>
<%--<form action="do" method="get">--%>
<%--<input type="hidden" name="action" value="show_edit_client_by_client_page"/>--%>
<%--<input type="hidden" name="user_id" value="${client.id}"/>--%>
<%--<input type="submit" value="Edit personal data"/>--%>
<%--</form>--%>

<%--<form action="do" method="get">--%>
<%--<input type="hidden" name="action" value="show_payment_page"/>--%>
<%--<input type="hidden" name="user_id" value="${client.id}"/>--%>
<%--<input type="submit" value="Make payment"/>--%>
<%--</form>--%>

<%--<form action="do" method="get">--%>
<%--<input type="hidden" name="action" value="show_change_tariff_page"/>--%>
<%--<input type="hidden" name="user_id" value="${client.id}"/>--%>
<%--<input type="submit" value="Change tariff"/>--%>
<%--</form>--%>

<%--<form action="do" method="get">--%>
<%--<input type="hidden" name="action" value="show_clients_payments_page"/>--%>
<%--&lt;%&ndash;<input type="hidden" name="user_id" value="${client.id}"/>&ndash;%&gt;--%>
<%--<input type="submit" value="My payments"/>--%>
<%--</form>--%>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>


</body>
</html>
