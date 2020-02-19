<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<fmt:setLocale value="${sessionScope.lang}"/>--%>
<%--<fmt:setBundle basename="messages"/>--%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">

    <title>Tariffs</title>
</head>

<body class="container">
<c:import url="/WEB-INF/jsp/footer.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

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

<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>