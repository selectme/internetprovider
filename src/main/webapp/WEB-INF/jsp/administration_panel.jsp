<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">
    <title>Administration panel</title>
</head>

<body class="container">
<c:import url="/WEB-INF/jsp/footer.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="row">
    <div class="col-sm-6">
        <div class="card bg-light">
            <div class="card-body">
                <h5 class="card-title">Clients</h5>
                <p class="card-text">Аdding, editing, removing clients</p>
                <a>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_users">
                        <input class="btn btn-primary shadow-lg" type="submit" value="Edit">
                    </form>
                </a>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="card bg-light">
            <div class="card-body">
                <h5 class="card-title">Tariff plans</h5>
                <p class="card-text">Аdding, editing, removing tariff plans</p>
                <a>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_tariffs">
                        <input class="btn btn-primary shadow-lg" type="submit" value="Edit">
                    </form>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
