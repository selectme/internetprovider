<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="card text-center">
    <div class="card-header">
        <fmt:message key="label.payment"/>
    </div>
    <div class="card-body">
        <h5 class="card-title"><fmt:message key="label.current_balance"/>: ${client.moneyOnAccount}</h5>
        <p class="card-text"><fmt:message key="label.replenish_money"/>:</p>

        <form action="do" method="post">
            <input type="hidden" name="action" value="make_payment"/>
            <input type="hidden" name="user_id" value="${client.id}"/>
            <div class="form-group row justify-content-center">
                <label for="passwordLabel" class="col-sm-2 col-form-label col-form-label-lg"><fmt:message
                        key="label.amount"/></label>
                <div class="col-sm-2">
                    <input type="number" name="amount" min="0.01" step="0.01" class="form-control form-control-lg"
                           id="passwordLabel"/>
                </div>
            </div>
            <div class="container">
                <div class="col text-center">
                    <button class="btn btn-default"><input type="submit" class="btn btn-success"
                                                           value="<fmt:message key="label.done"/>"></button>
                </div>
            </div>
        </form>
    </div>
    <div class="card-footer text-muted">
    </div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>
