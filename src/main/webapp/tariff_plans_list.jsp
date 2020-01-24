<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<button onclick="location.href='/'">Home</button>
<h2>Tariffs</h2>

${user.name} ${user.surname}
<form action="do" method="get">
    <input type="hidden" name="action" value="do_logout">
    <input type="submit" value="Logout">
</form>

<table border="2">
    <tr>
        <td>Title</td>
        <td>speed</td>
        <td>price</td>
    </tr>
    <c:forEach items="${tariffPlans}" var="tariffPlan">
        <tr>
            <td>${tariffPlan.title}</td>
            <td>${tariffPlan.speed}</td>
            <td>${tariffPlan.price}</td>
            <c:if test="${user.role == 'ADMIN'}">
                <td>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_edit_tariffplan_page"/>
                        <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                        <input type="submit" value="Edit"/>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<br>
<form action="do" method="get">
    <input type="hidden" name="action" value="add_tariff_page">
    <input type="submit" value="Add tariff">
</form>

</body>
</html>