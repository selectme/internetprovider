<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="../../css/bootstrap.css">

    <title>Payment...</title>
</head>
<body class="container">
<c:import url="/WEB-INF/jsp/footer.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="card text-center">
    <div class="card-header">
        Payment...
    </div>
    <div class="card-body">
        <h5 class="card-title">Current balance: ${client.moneyOnAccount}</h5>
        <p class="card-text">To replenish the account enter the amount:</p>

        <form action="do" method="post">
            <input type="hidden" name="action" value="make_payment"/>
            <input type="hidden" name="user_id" value="${client.id}"/>
            <div class="form-group row justify-content-center">
                <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg">Amount</label>
                <div class="col-sm-2">
                    <input type="number" name="amount" min="0" step="0.01" class="form-control form-control-lg"
                           id="passwordLabel"/>
                </div>
            </div>
            <div class="container">
                    <div class="col text-center">
                        <button class="btn btn-default"><input type="submit" class="btn btn-success" value="Done"></button>
                    </div>
            </div>
        </form>
    </div>
    <div class="card-footer text-muted">
    </div>
</div>

<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
