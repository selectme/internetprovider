<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<h2>
    <fmt:message key="label.chooseSessionLocale" />
</h2>



<div class="col border-bottom"><fmt:message key="label.welcome"/><c:out value="${user.name}"/> <c:out value="${user.surname}"/> !
</div>


<div class="jumbotron">
    <c:choose>
        <c:when test="${user != null}">
            <h1 class="display-4 ">Hello, <c:out value="${user.name}"/> <c:out value="${user.surname}"/>!</h1>
        </c:when>
        <c:otherwise>
            <h1 class="display-4">Hello!</h1>
        </c:otherwise>
    </c:choose>
    <p class="lead"> <fmt:message key="label.intro"/></p>
    <hr class="my-4">
    <p>To view our tariff plans, click the button below</p>
    <a>
        <form action="do" method="get">
            <input type="hidden" name="action" value="show_tariffs">
            <input class="btn btn-primary btn-lg shadow-lg p-3" type="submit" value="Tariff plans">
        </form>
    </a>
</div>

<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>

</body>
<c:import url="/WEB-INF/jsp/footer.jsp"/>
</html>