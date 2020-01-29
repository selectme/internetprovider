<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Viktar
  Date: 29.01.2020
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="do" method="post">
        <input type="hidden" name="action" value="edit_client_by_client"/>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
            </tr>
            <tr>
                <td><label><input type="number" name="user_id" value="${client.id}" readonly="readonly"/></label></td>
                <td><label><input type="text" name="name" value="${client.name}"/></label></td>
                <td><label><input type="text" name="surname" value="${client.surname}"/></label></td>
            </tr>
        </table>
        <input type="submit" value="Done"/>
    </form>
</body>
</html>
