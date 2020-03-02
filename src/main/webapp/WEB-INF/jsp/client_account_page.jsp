<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="row justify-content-center" style="padding: 70px 0">
    <div class="col-lg-2">
        <div class="btn-group-vertical">
            <form action="do" method="get">
                <input type="hidden" name="action" value="show_edit_client_by_client_page"/>
                <input type="hidden" name="user_id" value="${client.id}"/>
                <input type="submit" class="btn btn-primary btn-lg text-right" value="<fmt:message key="label.edit"/>"/>
            </form>
            <br>
            <form action="do" method="get">
                <input type="hidden" name="action" value="show_change_tariff_page"/>
                <input type="hidden" name="user_id" value="${client.id}"/>
                <input type="submit" class="btn btn-primary btn-lg" value="<fmt:message key="label.change_tariff"/>"/>
            </form>
            <br>
            <form action="do" method="get">
                <input type="hidden" name="action" value="show_payment_page"/>
                <input type="hidden" name="user_id" value="${client.id}"/>
                <input type="submit" class="btn btn-primary btn-lg" value="<fmt:message key="label.make_payment"/>"/>
            </form>
            <br>
            <form action="do" method="get">
                <input type="hidden" name="action" value="show_clients_payments_page"/>
                <input type="submit" class="btn btn-primary btn-lg" value="<fmt:message key="label.payments_history"/>"/>
            </form>
        </div>
    </div>
    <div class="col-md-auto">
        <div class="card col-auto" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title"><fmt:message key="label.my_page"/></h5>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><fmt:message key="label.login"/>: ${client.login}</li>
                <li class="list-group-item"><fmt:message key="label.user_name"/>: <c:out value="${client.name}"/> <c:out
                        value="${client.surname}"/></li>
                <li class="list-group-item"><fmt:message key="label.tariff"/>: <c:out
                        value="${client.getTariffPlan().getTitle()}"/></li>
                <li class="list-group-item"><fmt:message key="label.current_balance"/>: ${client.moneyOnAccount}</li>
                <c:choose>
                    <c:when test="${client.status=='BLOCKED'}">
                        <li class="list-group-item"><fmt:message key="label.status"/>: <fmt:message
                                key="label.blocked"/></li>
                    </c:when>
                    <c:when test="${client.status=='INACTIVE'}">
                        <li class="list-group-item"><fmt:message key="label.status"/>: <fmt:message
                                key="label.inactive"/></li>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item"><fmt:message key="label.status"/>: <fmt:message
                                key="label.active"/></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
    <div class="col-lg-2">
    </div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>