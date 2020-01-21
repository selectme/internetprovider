<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.01.2020
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center">
    <h1>Login</h1>
    <form action="/do" method="post">
        <input type="hidden" name="action" value="do_login">
        <label for="login">Login:</label>
        <input id="login" name="login" size="30" />
        <br><br>
        <label for="password">Password:</label>
        <input type="password" name="password" size="30" />
        <br>${message}
        <br><br>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
