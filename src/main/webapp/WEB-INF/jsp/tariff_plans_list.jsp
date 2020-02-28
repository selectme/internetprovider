<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="container">



    <c:if test="${user.role != 'ADMIN'}">
        <c:forEach items="${tariffPlans}" var="tariffPlan">
            <tr>
                <div class="card bg-light justify-content-center" align="center">
                    <div class="card-body">
                        <div class="badge badge-light text-wrap border-bottom shadow">
                            <h4 class="card-title"><c:out value="${tariffPlan.title}"/></h4>
                        </div>
                        <br>
                        <div class="badge badge-light">
                            <p class="card-text">
                            <h5><fmt:message key="label.speed"/>: ${tariffPlan.speed} mb/s</h5></p>
                        </div>
                        <br>
                        <div class="badge badge-light">
                            <p class="card-text">
                            <h5><fmt:message key="label.price"/>: ${tariffPlan.price}</h5></p>
                        </div>
                        <br>

                        <c:choose>
                            <c:when test="${user == null}">
                                <form action="do" method="get">
                                    <input type="hidden" name="action" value="show_login_page"/>
                                    <input type="submit" class="btn-primary" value="<fmt:message key="label.choose"/>"/>
                                </form>
                            </c:when>
                            <c:when test="${user.role == 'CLIENT'}">
                                <form action="do" method="get">
                                    <input type="hidden" name="action" value="show_change_tariff_page"/>
                                    <input type="hidden" name="user_id" value="${user.id}"/>
                                    <input type="submit" class="btn-primary" value="<fmt:message key="label.choose"/>"/>
                                </form>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </tr>
            <br> <br>
        </c:forEach>
    </c:if>

    <c:if test="${user.role == 'ADMIN'}">
        <div class="mt-4 mb-4">
            <a href="do?action=show_add_tariff_page">
                <button class="btn btn-primary"><fmt:message key="label.add"/></button>
            </a>
        </div>
            <table class="table table-sm table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col"><fmt:message key="label.title"/></th>
                    <th scope="col"><fmt:message key="label.speed"/></th>
                    <th scope="col"><fmt:message key="label.price"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tariffPlans}" var="tariff">
                    <tr>
                        <td>${tariff.id}</td>
                        <td><c:out value="${tariff.title}"/></td>
                        <td>${tariff.speed}</td>
                        <td>${tariff.price}</td>
                        <td>
                            <form action="do" method="get">
                                <input type="hidden" name="action" value="show_edit_tariffplan_page"/>
                                <input type="hidden" name="tariff_id" value="${tariff.id}"/>
                                <input class="btn btn-light btn" type="submit" value="<fmt:message key="label.edit"/>"/>
                            </form>
                        </td>
                        <td>
                            <form action="do" method="post">
                                <input type="hidden" name="action" value="delete_tariff_plan"/>
                                <input type="hidden" name="tariff_id" value="${tariff.id}"/>
                                <input class="btn btn-light btn" type="submit"
                                       value="<fmt:message key="label.delete"/>"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

    </c:if>
</div>
<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
