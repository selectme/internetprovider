<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.01.2020
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${user.role == 'ADMIN'}">
<form action="do" method="post">
    <input type="hidden" name="action" value="edit_user_by_admin"/>
    <table>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Tariff</th>
            <th>Status</th>
        </tr>
        <tr>
            <td><label><input type="number" name="user_id" value="${client.id}" readonly="readonly"/></label></td>
            <td><label><input type="number" name="login" value="${client.login}" readonly="readonly"/></label></td>
            <td><label><input type="text" name="name" value="${client.name}"/></label></td>
            <td><label><input type="text" name="surname" value="${client.surname}"/></label></td>
            <td><label>
                <select name="tariff_id">
                    <c:forEach items="${tariffPlans}" var="tariffPlan">
                        <option value="${tariffPlan.id}">${tariffPlan.title}</option>
                        <option selected hidden value="${client.tariffPlan.id}">${client.tariffPlan.title}</option>
                    </c:forEach>
                </select>
            </label>
            </td>
            <td><label>
                <select name="status">
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="INACTIVE">INACTIVE</option>
                        <option value="BLOCKED">BLOCKED</option>
                        <option selected hidden value="${client.status}">${client.status}</option>
                </select>
            </label>
            </td>
        </tr>
    </table>
    <input type="submit" value="Done"/>
</form>

</c:if>

</body>
</html>
