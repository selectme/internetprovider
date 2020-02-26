<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<br>
<div class="container w-50">
    <form action="do" method="post">
        <input type="hidden" name="action" value="edit_client_by_client"/>
        <div class="form-group row">
            <div class="col-sm-10">
                <input type="hidden" name="user_id" value="${client.id}" class="form-control form-control-sm" id="colFormLabelSm"
                       placeholder="col-form-label-sm"
                       readonly="readonly">
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

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <button class="btn btn-default"><input type="submit" class="btn btn-success" value="<fmt:message key="label.done"/>"></button>
                </div>
            </div>
        </div>
        <c:if test="${not empty error}">
            <fmt:message key='${error}'/>
        </c:if>
    </form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>
