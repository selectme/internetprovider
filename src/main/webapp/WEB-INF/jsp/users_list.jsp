<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="container">

    <%--<form action="do" method="get">--%>
    <%--<input type="hidden" name="action" value="show_add_client_page">--%>
    <%--<input class="btn btn-light" type="submit" value="<fmt:message key="label.add"/>">--%>
    <%--</form>--%>

    <div class="mt-4 mb-4">
        <a href="do?action=show_add_client_page">
            <button class="btn btn-primary"><fmt:message key="label.add"/></button>
        </a>
    </div>

    <table class="table table-sm table-hover">
        <thead class="table-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col"><fmt:message key="label.login"/></th>
            <th scope="col"><fmt:message key="label.user_name"/></th>
            <th scope="col"><fmt:message key="label.user_surname"/></th>
            <th scope="col"><fmt:message key="label.tariff"/></th>
            <th scope="col"><fmt:message key="label.status"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>${client.id}</td>
                <td>${client.login}</td>
                <td><c:out value="${client.name}"/></td>
                <td><c:out value="${client.surname}"/></td>
                <td><c:out value="${client.getTariffPlan().getTitle()}"/></td>
                <c:choose>
                    <c:when test="${client.status=='BLOCKED'}">
                        <td class="bg-danger"><fmt:message key="label.blocked"/></td>
                    </c:when>
                    <c:when test="${client.status=='INACTIVE'}">
                        <td class="bg-warning"><fmt:message key="label.inactive"/></td>
                    </c:when>
                    <c:otherwise>
                        <td class="bg-success"><fmt:message key="label.active"/></td
                    </c:otherwise>
                </c:choose>

                <td>
                    <form action="do" method="get">
                        <input type="hidden" name="action" value="show_edit_user_page_by_admin"/>
                        <input type="hidden" name="user_id" value="${client.id}"/>
                        <input class="btn btn-light" type="submit" value="<fmt:message key="label.edit"/>"/>
                    </form>
                </td>
                <td>
                    <form action="do" method="post">
                        <input type="hidden" name="action" value="delete_user"/>
                        <input type="hidden" name="user_id" value="${client.id}"/>
                        <input class="btn btn-light btn" type="submit" value="<fmt:message key="label.delete"/>"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>

<%--<c:import url="/WEB-INF/jsp/footer.jsp"/>--%>
