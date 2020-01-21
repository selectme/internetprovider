<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>hello</h2>

${user.name} ${user.surname}
<form action="/do" method="get">
    <input type="hidden" name="action" value="do_logout">
    <input type="submit" value="Logout">
</form>

<table border="2">
    <tr>
        <td>Title</td>
        <td>speed</td>
        <td>price</td>
    </tr>
    <c:forEach items="${tariffPlans}" var="tarifPlan">
    <tr>
        <td>${tarifPlan.title}</td>
        <td>${tarifPlan.speed}</td>
        <td>${tarifPlan.price}</td>
    </tr>
    </c:forEach>
</body>
</html>