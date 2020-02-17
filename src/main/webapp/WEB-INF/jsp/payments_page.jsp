<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">

    <title>Payments</title>
</head>
<body class="container">
<c:import url="/WEB-INF/jsp/footer.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="container">
    <table class="table table-sm table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Amount</th>
            <th scope="col">Type</th>
            <th scope="col">Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${payments}" var="payment">
            <tr>
                <td>${payment.amount}</td>
                <td>${payment.paymentType}</td>
                <td>${payment.date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
