<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<br>
<div class="container w-50">
    <form action="do" method="post">
        <input type="hidden" name="action" value="add_user">

        <div class="form-group row">
            <label for="inputLogin" class="col-sm-2 col-form-label col-form-label-lg">Login</label>
            <div class="col-sm-10">
                <label>
                    <input name="login" class="form-control form-control-lg" id="inputLogin"
                           pattern="([0-9]{4}|admin)"
                           required/>
                </label>
                <small id="loginHelp" class="form-text text-muted">Login is 4 digit sequence</small>
            </div>
        </div>

        <div class="form-group row">
            <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg">Password</label>
            <div class="col-sm-10">
                <label>
                    <input type="password" name="password" class="form-control form-control-lg"
                           id="passwordLabel"/>
                </label>
            </div>
        </div>

        <div class="form-group row">
            <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg">Name</label>
            <div class="col-sm-10">
                <label>
                    <input name="name" class="form-control form-control-lg" required/>
                </label>
            </div>
        </div>

        <div class="form-group row">
            <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg">Surname</label>
            <div class="col-sm-10">
                <label>
                    <input name="surname" class="form-control form-control-lg" required/>
                </label>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Role</label>
            <div class="col-sm-10">
                <label>
                    <select class="form-control" name="role">
                        <option value="CLIENT">Client</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </label>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button class="btn btn-default"><input type="submit" class="btn btn-success" value="Done"></button>
                    <p class="font-weight-normal">${error}</p>
                </div>
            </div>
        </div>
    </form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>
