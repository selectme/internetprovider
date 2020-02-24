<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row justify-content-lg-center">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title"><fmt:message key="label.current_balance"/>:</h5>
                <h4 class="card-subtitle mb-2 text-muted">${client.moneyOnAccount}</h4>
                <form class="button" action="do" method="get">
                    <input type="hidden" name="action" value="show_payment_page"/>
                    <input type="hidden" name="user_id" value="${client.id}"/>
                    <input class="btn-success" type="submit" value="<fmt:message key="label.make_payment"/>"/>
                </form>
            </div>
        </div>
    </div>
</div>
<br>

<div class="card-deck row row-cols-1 row-cols-md-3">
    <c:forEach items="${tariffPlans}" var="tariffPlan">
        <div class="col mb-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title" align="center"><c:out value="${tariffPlan.title}"/></h5>
                    <p class="card-text" align="center">
                        <fmt:message key="label.speed"/>: ${tariffPlan.speed}
                    </p>
                    <p class="card-text" align="center">
                        <fmt:message key="label.price"/>: ${tariffPlan.price}
                    </p>
                    <c:choose>
                        <c:when test="${client.getTariffPlan().getTitle()==tariffPlan.title}">
                            <form class="button" action="do" method="post" align="center">
                                <input type="hidden" name="action" value="change_tariff_plan"/>
                                <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                                <input name="user_id" value="${client.id}" hidden/>
                                <input class="btn-success" type="submit" value="<fmt:message key="label.my.tariff"/>" disabled/>
                            </form>
                        </c:when>
                        <c:when test="${client.moneyOnAccount == null || client.moneyOnAccount  < tariffPlan.price}">
                            <form class="button" action="do" method="post" align="center">
                                <input type="hidden" name="action" value="change_tariff_plan"/>
                                <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                                <input name="user_id" value="${client.id}" hidden/>
                                <input class="btn-secondary" type="submit" value="<fmt:message key="label.no_money"/>" disabled/>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form class="button" action="do" method="post" align="center">
                                <input type="hidden" name="action" value="change_tariff_plan"/>
                                <input name="tariff_id" value="${tariffPlan.id}" hidden/>
                                <input name="user_id" value="${client.id}" hidden/>
                                <input class="btn-primary" type="submit" value="<fmt:message key="label.connect"/>"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>
