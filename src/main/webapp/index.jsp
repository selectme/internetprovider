<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Здравствуйте!</title>
</head>
<body>
<div style="text-align: center">

    <h1>Welcome ${user.name} ${user.surname}!</h1>

    <form action="login.jsp">
        <input type="submit" value="Login">
    </form>

    <form action="/do" method="get">
        <input type="hidden" name="action" value="show_users">
        <input type="submit" value="Clients">
    </form>

<form action="/do" method="get">
    <input type="hidden" name="action" value="show_tariffs">
    <input type="submit" value="Тарифные планы">
</form>
</div>
</body>
</html>
