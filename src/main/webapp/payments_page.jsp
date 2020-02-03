<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button onclick="location.href='/'">Home</button>
<table border="2">
    <tr>
        <td>Amount</td>
        <td>Type</td>
        <td>Date</td>
    </tr>
    <c:forEach items="${payments}" var="payment">
        <tr>
            <td>${payment.amount}</td>
            <td>${payment.paymentType}</td>
            <td>${payment.date}</td>
        </tr>
    </c:forEach>
    <br>
</table>
</body>
</html>
