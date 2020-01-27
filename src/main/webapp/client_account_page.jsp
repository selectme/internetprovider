<%--
  Created by IntelliJ IDEA.
  User: Viktar
  Date: 26.01.2020
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button onclick="location.href='/'">Home</button>
<table border="2">
    <tr>
        <td>Login</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Tariff Plane</td>
        <td>Money balance</td>
    </tr>
    <tr>
        <td>${client.getLogin()}</td>
        <td>${client.name}</td>
        <td>${client.surname}</td>
        <td>${client.getTariffPlan().getTitle()}</td>
        <td>${client.moneyOnAccount}</td>
    </tr>
    <br>
</table>
<form action="do" method="get">
    <input type="hidden" name="action" value="show_edit_user_page"/>
    <input type="hidden" name="user_id" value="${client.id}"/>
    <input type="submit" value="Edit personal data"/>
</form>

<form action="do" method="get">
    <input type="hidden" name="action" value="show_payment_page"/>
    <input type="hidden" name="user_id" value="${client.id}"/>
    <input type="submit" value="Make payment"/>
</form>
</body>
</html>