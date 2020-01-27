<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Viktar
  Date: 27.01.2020
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="do" method="get">
    <input type="hidden" name="action" value="show_client_account_page">
    <input name="user_id" value="${client.id}" hidden/>
    <input type="submit" value="My account">
</form>
Your balance:
<div>
    <table border="2">
        <tr>
            <td>Money</td>
        </tr>
        <tr>
            <td>${client.moneyOnAccount}</td>
            <td>
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_payment_page"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input type="submit" value="Make payment"/>
                </form>
            </td>
        </tr>

    </table>
</div>
Your tariff plane:
<div>
    <table border="2">
        <tr>
            <td>Title</td>
            <td>speed</td>
            <td>price</td>
        </tr>
        <tr>
            <td>${tariff.title}</td>
            <td>${tariff.speed}</td>
            <td>${tariff.price}</td>
        </tr>
    </table>
</div>
Choose new tariff plan:
<div>
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

                <td>
                    <form action="do" method="post">
                        <input type="hidden" name="action" value="change_tariff_plan"/>
                        <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                        <input name="user_id" value="${client.id}" hidden/>
                        <input type="submit" value="Connect"/>
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
<br>
${message}
</body>
</html>
