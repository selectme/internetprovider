<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="jumbotron">
    <c:choose>
        <c:when test="${user != null}">
            <h1 class="display-4 "><fmt:message key="label.hello"/>, <c:out value="${user.name}"/> <c:out
                    value="${user.surname}"/>!</h1>
        </c:when>
        <c:otherwise>
            <h1 class="display-4"><fmt:message key="label.hello"/>!</h1>
        </c:otherwise>
    </c:choose>
    <p class="lead"><fmt:message key="label.intro"/></p>
    <hr class="my-4">
    <p><fmt:message key="label.second_intro"/></p>
    <a>
        <form action="do" method="get">
            <input type="hidden" name="action" value="show_tariffs">
            <fmt:message key="label.tariffPlans" var="tariffs"/>
            <input class="btn btn-primary btn-lg shadow-lg p-3" type="submit" value="${tariffs}">
        </form>
    </a>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>


