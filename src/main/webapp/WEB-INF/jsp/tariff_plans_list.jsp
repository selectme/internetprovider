<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">

    <title>Login</title>
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

<c:if test="${user.role != 'ADMIN'}">
<c:forEach items="${tariffPlans}" var="tariffPlan">
    <tr>
        <div class="card bg-light justify-content-center" align="center">
            <div class="card-body">
                <div class="badge badge-light text-wrap border-bottom shadow">
                    <h4 class="card-title"><c:out value="${tariffPlan.title}"/></h4>
                </div>
                <br>
                <div class="badge badge-light">
                    <p class="card-text">
                    <h5>Speed: ${tariffPlan.speed} mb/s</h5></p>
                </div>
                <br>
                <div class="badge badge-light">
                    <p class="card-text">
                    <h5>Price: ${tariffPlan.price} rub</h5></p>
                </div>
                <br>

                <c:choose>
                    <c:when test="${user == null}">
                        <form action="do" method="get">
                            <input type="hidden" name="action" value="show_login_page"/>
                            <input type="submit" class="btn-primary" value="Choose"/>
                        </form>
                    </c:when>
                    <c:when test="${user.role == 'CLIENT'}">
                        <form action="do" method="get">
                            <input type="hidden" name="action" value="show_change_tariff_page"/>
                            <input type="hidden" name="user_id" value="${user.id}"/>
                            <input type="submit" class="btn-primary" value="Choose"/>
                        </form>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </tr>
    <br> <br>
</c:forEach>
</c:if>

<c:if test="${user.role == 'ADMIN'}">
<div class="container">
    <table class="table table-sm table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Title</th>
            <th scope="col">Speed</th>
            <th scope="col">Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tariffPlans}" var="tariff">
            <tr>
                <td>${tariff.id}</td>
                <td><c:out value="${tariff.title}"/></td>
                <td>${tariff.speed}</td>
                <td>${tariff.price}</td>
                <td>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_edit_tariffplan_page"/>
                        <input type="hidden" name="tariff_id" value="${tariff.id}"/>
                        <input class="btn btn-light btn" type="submit" value="Edit"/>
                    </form>
                </td>
                <td>
                    <form action="do" method="post">
                        <input type="hidden" name="action" value="delete_tariff_plan"/>
                        <input type="hidden" name="tariff_id" value="${tariff.id}"/>
                        <input class="btn btn-light btn" type="submit" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="do" method="get">
        <input type="hidden" name="action" value="show_add_tariff_page">
        <input class="btn btn-light" type="submit" value="Add tariff">
    </form>
</div>
</c:if>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>