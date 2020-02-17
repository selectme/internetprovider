
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">
    <title>Login</title>
</head>
<body>

<h1 align="center" class="font-weight-light">Login</h1>

<div class="container w-25">
    <form action="do" method="post">
        <div class="form-group">
            <input type="hidden" name="action" value="do_login">
            <label for="inputLogin">Login</label>
            <input name="login" class="form-control no-spinner" id="inputLogin" aria-describedby="loginHelp"
                   pattern="([0-9]{4}|admin)" required>
            <small id="loginHelp" class="form-text text-muted">Login is 4 digit sequence</small>
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" name="password" class="form-control" id="inputPassword">
        </div>
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </div>
        <br>
        <p class="font-italic">${error}</p>
    </form>

</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
