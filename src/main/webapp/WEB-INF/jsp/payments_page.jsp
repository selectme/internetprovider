<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <table class="table table-sm table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col"><fmt:message key="label.amount"/></th>
            <th scope="col"><fmt:message key="label.type_payment"/></th>
            <th scope="col"><fmt:message key="label.date_payment"/></th>
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

