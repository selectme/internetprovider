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
        <td>${tarifPlan.title}</td>
        <td>${tarifPlan.speed}</td>
        <td>${tarifPlan.tariffType}</td>
        <td>${tarifPlan.price}</td>
    </tr>
    </c:forEach>
</body>
</html>