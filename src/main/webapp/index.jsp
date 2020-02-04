<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">

    <title>Hello</title>
</head>

<body class="container bg-light">

<div class="row">
    <div class="col-5"></div>
    <div class="col border-bottom"> Welcome ${user.name} ${user.surname} !</div>

    <c:if test="${user != null}">
        <div class="col border-bottom">
            <c:if test="${user.role == 'CLIENT'}">
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_client_account_page">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input class="btn btn-light" type="submit" value="My account">
                </form>
            </c:if>
            <c:if test="${user.role == 'ADMIN'}">
                <form action="do" method="get">
                    <input type="hidden" name="action" value="show_administration_panel">
                    <input class="btn btn-light" type="submit" value="Administration panel">
                </form>
            </c:if>
        </div>
        <div class="col border-bottom">
            <form action="do" method="get">
                <input type="hidden" name="action" value="do_logout">
                <input class="btn btn-light" type="submit" value="Logout">
            </form>
        </div>
    </c:if>
    <div class="col border-bottom">
        <c:if test="${user == null}">
            <form action="do" method="get">
                <input type="hidden" name="action" value="show_login_page"/>
                <input class="btn btn-light" type="submit" value="Login">
            </form>
        </c:if>
    </div>
</div>

<div class="card" style="width: 18rem;">
    <img src="..." class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
</div>

<section>
    <div class="container">
        <div class="row">
            <form action="do" method="get">
                <input type="hidden" name="action" value="show_tariffs">
                <input class="btn btn-light" type="submit" value="Tariff plans">
            </form>
        </div>
    </div>
</section>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
