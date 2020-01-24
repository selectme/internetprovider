<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.01.2020
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="do" method="post">
<input type="hidden" name="action" value="editTariffPlan">
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Speed</th>
            <th>Price</th>
        </tr>
        <tr>
            <td><label><input type="number" name="id" value="${tariffPlan.id}" readonly="readonly"/></label></td>
            <td><label><input type="text" name="title" value="${tariffPlan.title}"/></label></td>
            <td><label><input type="number" name="speed" value="${tariffPlan.speed}"/></label></td>
            <td><label><input type="number" name="price" value="${tariffPlan.price}" step="0.01"/></label></td>
        </tr>
    </table>
    <input type="submit" value="Done">
</form>
</body>
</html>
