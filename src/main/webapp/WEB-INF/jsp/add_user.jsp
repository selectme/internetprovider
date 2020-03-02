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
            <label for="inputLogin" class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.login"/></label>
            <div class="col-sm-10">
                <label>
                    <input name="login" type="number" class="form-control form-control-lg" id="inputLogin"
                           pattern="([0-9]{4})" max="9999"
                           required/>
                </label>
                <small id="loginHelp" class="form-text text-muted"><fmt:message key="label.login.helper"/></small>
            </div>
        </div>

        <div class="form-group row">
            <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.password"/></label>
            <div class="col-sm-10">
                <label>
                    <input type="password" name="password" class="form-control form-control-lg"
                           id="passwordLabel" required/>
                </label>
            </div>
        </div>

        <div class="form-group row">
            <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.user_name"/></label>
            <div class="col-sm-10">
                <label>
                    <input name="name" class="form-control form-control-lg" pattern="^[A-Za-zА-Яа-я\s]+[\.\']?[A-Za-zА-Яа-я\s]*$" required/>
                </label>
            </div>
        </div>

        <div class="form-group row">
            <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.user_surname"/></label>
            <div class="col-sm-10">
                <label>
                    <input name="surname" class="form-control form-control-lg" pattern="^[A-Za-zА-Яа-я\s]+[\.\']?[A-Za-zА-Яа-я\s]*$" required/>
                </label>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.role"/></label>
            <div class="col-sm-10">
                <label>
                    <select class="form-control" name="role">
                        <option value="CLIENT"><fmt:message key="label.client"/></option>
                        <option value="ADMIN"><fmt:message key="label.admin"/></option>
                    </select>
                </label>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button class="btn btn-default"><input type="submit" class="btn btn-success" value="<fmt:message key="label.done"/>"></button>
                </div>
            </div>
            <c:if test="${not empty error}">
                <fmt:message key='${error}'/>
            </c:if>
        </div>
    </form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>
