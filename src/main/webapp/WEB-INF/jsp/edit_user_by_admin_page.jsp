<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/main.css">
    <title>Editing...</title>
</head>
<body class="container">
<c:import url="/WEB-INF/jsp/footer.jsp"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<br>
<div class="container w-50">
    <form action="do" method="post">
        <input type="hidden" name="action" value="edit_user_by_admin"/>

        <div class="form-group row">
            <label for="titleLabel" class="col-sm-2 col-form-label col-form-label-lg">ID</label>
            <div class="col-sm-10">
                <input name="user_id" value="${client.id}" class="form-control form-control-sm" id="colFormLabelSm"
                       placeholder="col-form-label-sm"
                       readonly="readonly">
            </div>
        </div>

        <div class="form-group row">
            <label for="titleLabel" class="col-sm-2 col-form-label col-form-label-lg">Login</label>
            <div class="col-sm-10">
                <input name="Login" value="${client.getLogin()}" class="form-control form-control-lg" id="titleLabel"
                       readonly="readonly"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Name</label>
            <div class="col-sm-10">
                <input type="text" name="name" value="${client.name}" class="form-control form-control-lg"
                       id="speedLabel" required/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Surname</label>
            <div class="col-sm-10">
                <input type="text" name="surname" value="${client.surname}"
                       class="form-control form-control-lg" required/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Tariff</label>
            <div class="col-sm-10">
                <select class="form-control form-control-lg" name="tariff_id">--%>
                    <c:forEach items="${tariffPlans}" var="tariffPlan">
                        <option value="${tariffPlan.id}"><c:out value="${tariffPlan.title}"/></option>
                        <option selected hidden value="${client.tariffPlan.id}"><c:out
                                value="${client.tariffPlan.title}"/></option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Status</label>
            <div class="col-sm-10">
                <select class="form-control form-control-lg" name="status">
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="INACTIVE">INACTIVE</option>
                    <option value="BLOCKED">BLOCKED</option>
                    <option selected hidden value="${client.status}">${client.status}</option>
                </select>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button class="btn btn-default"><input type="submit" class="btn btn-success" value="Done"></button>
                </div>
            </div>
        </div>
    </form>
    ${error}
</div>

<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>

</html>
