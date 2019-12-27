<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>hello</h2>

${test}
<c:out value="123"/>

<table border="2">
    <tr>
        <td>Title</td>
        <td>speed</td>
        <td>type</td>
        <td>price</td>
    </tr>
    <c:forEach items="${tariffPlans}" var="tarifPlan">
    <tr>
        <td>${tarifPlan.getTitle}</td>
        <td>${tarifPlan.getSpeed}</td>
        <td>${tarifPlan.getTariffType}</td>
        <td>${tarifPlan.getPrice}</td>
    </tr>
    </c:forEach>
</body>
</html>