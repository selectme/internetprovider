<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

    <div class="row">
        <div class="col-sm-6">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title"><fmt:message key="label.clients"/></h5>
                    <p class="card-text"><fmt:message key="label.description.clients"/></p>
                    <a>
                        <form action="do" method="get">
                            <input type="hidden" name="action" value="show_users">
                            <input class="btn btn-primary shadow-lg" type="submit"
                                   value="<fmt:message key="label.edit"/>">
                        </form>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title"><fmt:message key="label.tariffPlans"/></h5>
                    <p class="card-text"><fmt:message key="label.description.tariffs"/></p>
                    <a>
                        <form action="do" method="get">
                            <input type="hidden" name="action" value="show_tariffs">
                            <input class="btn btn-primary shadow-lg" type="submit"
                                   value="<fmt:message key="label.edit"/>">
                        </form>
                    </a>
                </div>
            </div>
        </div>
    </div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>
