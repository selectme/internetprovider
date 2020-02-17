<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">
    <title>Change tariff</title>
</head>

<body class="container">
<c:import url="/WEB-INF/jsp/footer.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row justify-content-lg-center">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Current balance:</h5>
                <h4 class="card-subtitle mb-2 text-muted">${client.moneyOnAccount} rub </h4>
                <form class="button" action="do" method="get">
                    <input type="hidden" name="action" value="show_payment_page"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input class="btn-success" type="submit" value="Make payment"/>
                </form>
            </div>
        </div>
    </div>
</div>
<br>

<div class="card-deck row row-cols-1 row-cols-md-3">
    <c:forEach items="${tariffPlans}" var="tariffPlan">
        <div class="col mb-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title" align="center"><c:out value="${tariffPlan.title}"/></h5>
                    <p class="card-text" align="center">
                        Speed: ${tariffPlan.speed}
                    </p>
                    <p class="card-text" align="center">
                        Price: ${tariffPlan.price}
                    </p>
                    <c:choose>
                        <c:when test="${client.getTariffPlan().getTitle()==tariffPlan.title}">
                            <form class="button" action="do" method="post" align="center">
                                <input type="hidden" name="action" value="change_tariff_plan"/>
                                <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                                <input name="user_id" value="${client.id}" hidden/>
                                <input class="btn-success" type="submit" value="My tariff" disabled/>
                            </form>
                        </c:when>
                        <c:when test="${client.moneyOnAccount == null || client.moneyOnAccount  < tariffPlan.price}">
                            <form class="button" action="do" method="post" align="center">
                                <input type="hidden" name="action" value="change_tariff_plan"/>
                                <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                                <input name="user_id" value="${client.id}" hidden/>
                                <input class="btn-secondary" type="submit" value="Not enough money" disabled/>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form class="button" action="do" method="post" align="center">
                                <input type="hidden" name="action" value="change_tariff_plan"/>
                                <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                                <input name="user_id" value="${client.id}" hidden/>
                                <input class="btn-primary" type="submit" value="Connect"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
