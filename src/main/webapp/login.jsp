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
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>Login</title>
</head>
<body>
<div style="text-align: center">
    <h1>Login</h1>
    <form action="do" method="post">
        <input type="hidden" name="action" value="do_login">
        <label>Login:</label>
        <input id="login" name="login" size="30" pattern="([0-9]{4}|admin)" required/>
        <br><br>
        <label>Password:</label>
        <input type="password" id="password" name="password" size="30" required/>
        <br>
        <br class="alert-warning">${message}<br>
        <button type="submit">Login</button>
    </form>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
