<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<h1 align="center" class="font-weight-light"><fmt:message key="label.log.in"/></h1>

<div class="container w-25">
    <form action="do" method="post">
        <div class="form-group">
            <input type="hidden" name="action" value="do_login">
            <label for="inputLogin"><fmt:message key="label.login"/></label>
            <input name="login" class="form-control no-spinner" id="inputLogin" aria-describedby="loginHelp"
                   pattern="([0-9]{4}|admin)" required>
            <small id="loginHelp" class="form-text text-muted"><fmt:message key="label.login.helper"/></small>
        </div>
        <div class="form-group">
            <label for="inputPassword"><fmt:message key="label.password"/></label>
            <input type="password" name="password" class="form-control" id="inputPassword">
        </div>
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.submit"/></button>
                </div>
            </div>
        </div>
        <br>
        <c:if test="${not empty error}">
            <fmt:message key='${error}'/>
        </c:if>
        <p class="font-italic">
        </p>
    </form>

</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>
