<%--
  Created by IntelliJ IDEA.
  User: Viktar
  Date: 26.01.2020
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="do" method="post">
    <input type="hidden" name="action" value="make_payment"/>
    <input type="hidden" name="user_id" value="${client.id}"/>
    <label>
        Amount:
        <input type="number" name="amount" min="0"></label>

    </label>
    <input type="submit" value="Pay"/>
</form>
</body>
</html>
