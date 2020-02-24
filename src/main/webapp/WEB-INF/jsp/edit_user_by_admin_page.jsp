<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<br>
<div class="container w-50">
    <form action="do" method="post">
        <input type="hidden" name="action" value="edit_user_by_admin"/>

        <div class="form-group row">
            <label for="titleLabel" class="col-sm-2 col-form-label col-form-label-lg">ID</label>
            <div class="col-sm-10">
                <input name="user_id" value="${client.id}" class="form-control form-control-sm" id="colFormLabelSm"
                       placeholder="col-form-label-sm"
                       readonly="readonly">
            </div>
        </div>

        <div class="form-group row">
            <label for="titleLabel" class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.login"/></label>
            <div class="col-sm-10">
                <input name="Login" value="${client.getLogin()}" class="form-control form-control-lg" id="titleLabel"
                       readonly="readonly"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.user_name"/></label>
            <div class="col-sm-10">
                <input type="text" name="name" value="${client.name}" class="form-control form-control-lg"
                       id="speedLabel" required/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.user_surname"/></label>
            <div class="col-sm-10">
                <input type="text" name="surname" value="${client.surname}"
                       class="form-control form-control-lg" required/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.tariff"/></label>
            <div class="col-sm-10">
                <select class="form-control form-control-lg" name="tariff_id">--%>
                    <c:forEach items="${tariffPlans}" var="tariffPlan">
                        <option value="${tariffPlan.id}"><c:out value="${tariffPlan.title}"/></option>
                        <option selected hidden value="${client.tariffPlan.id}"><c:out
                                value="${client.tariffPlan.title}"/></option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg"><fmt:message key="label.status"/></label>
            <div class="col-sm-10">
                <select class="form-control form-control-lg" name="status">
                    <option value="ACTIVE"><fmt:message key="label.active"/></option>
                    <option value="INACTIVE"><fmt:message key="label.inactive"/></option>
                    <option value="BLOCKED"><fmt:message key="label.blocked"/></option>
                    <c:choose>
                        <c:when test="${client.status=='ACTIVE'}">
                            <option selected hidden value="ACTIVE"><fmt:message key="label.active"/> </option>
                        </c:when>
                        <c:when test="${client.status=='BLOCKED'}">
                            <option selected hidden value="BLOCKED"><fmt:message key="label.blocked"/> </option>
                        </c:when>
                        <c:otherwise>
                                <option selected hidden value="INACTIVE"><fmt:message key="label.inactive"/> </option>
                        </c:otherwise>
                    </c:choose>
<%--                    <option selected hidden value="${client.status}">${client.status}</option>--%>
                </select>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button class="btn btn-default"><input type="submit" class="btn btn-success" value="<fmt:message key="label.done"/>"></button>
                </div>
            </div>
        </div>
    </form>
    <c:if test="${not empty error}">
        <fmt:message key='${error}'/>
    </c:if>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>