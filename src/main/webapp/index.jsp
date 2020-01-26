<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Здравствуйте!</title>
</head>
<body>
<div style="text-align: center">

    <h1>Welcome ${user.name} ${user.surname} ${user.role}!</h1>

    <c:if test="${user.role=='ADMIN'}">
        <form action="/do" method="get">
            <input type="hidden" name="action" value="show_users">
            <input type="submit" value="Clients">
        </form>
    </c:if>

    <c:if test="${user != null}">
        <form action="do" method="get">
            <input type="hidden" name="action" value="do_logout">
            <input type="submit" value="Logout">
        </form>
    </c:if>

    <c:if test="${user == null}">
        <form action="do" method="get">
            <input type="hidden" name="action" value="show_login_page"/>
            <input type="submit" value="Login">
        </form>
    </c:if>

<form action="/do" method="get">
    <input type="hidden" name="action" value="show_tariffs">
    <input type="submit" value="Тарифные планы">
</form>

</div>
</body>
</html>
