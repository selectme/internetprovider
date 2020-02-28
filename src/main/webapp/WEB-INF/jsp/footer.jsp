<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<footer class="navbar fixed-bottom justify-content-center">
    <%--<nav aria-label="breadcrumb" style="height: 50px">--%>
        <%--<ol class="breadcrumb justify-content-center">--%>
            <%--<li> Copyright © 2020 All rights reserved.</li>--%>
        <%--</ol>--%>
    <%--</nav>--%>
    <div>
        <fmt:message key="label.copyright"/>
    </div>
</footer>


<script src="../../js/jquery-3.4.1.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
