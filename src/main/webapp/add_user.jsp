<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.01.2020
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="do" method="post">
    <input type="hidden" name="action" value="add_user">
    <label>Login:
        <input type="text" name="login" required/>
    </label>
    <br>
    <label>Password:
        <input type="password" name="password" required/>
    </label>
    <br>
    <label>Name:
        <input type="text" name="name" required/>
    </label>
    <br>
    <label>Surname:
        <input type="text" name="surname" required/>
    </label>
    <br>
    <label>Role:
        <select name="role">
            <option value="ADMIN">Admin</option>
            <option value="CLIENT">Client</option>
        </select>
    </label>
    <br>
    <input type="submit" value="Add">
</body>
</html>
