<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">

    <title><c:out value="${user.name}"/> <c:out value="${user.surname}"/></title>
</head>
<body class="container">
<c:import url="/WEB-INF/jsp/footer.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

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
                    <li class="list-group-item">Name: <c:out value="${user.name}"/> <c:out value="${user.surname}"/></li>
                    <li class="list-group-item">Tariff: <c:out value="${client.getTariffPlan().getTitle()}"/></li>
                    <li class="list-group-item">Balance: ${client.moneyOnAccount}</li>
                    <li class="list-group-item">Status: ${client.status}</li>
                </ul>
            </div>
        </div>
        <div class="col-lg-2">
        </div>
    </div>
</div>


<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>

</body>
</html>
