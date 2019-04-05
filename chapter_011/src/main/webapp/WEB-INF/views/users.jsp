<%@ page import="ru.job4j.servlets.users.logic.ValidateService" %>
<%@ page import="ru.job4j.servlets.users.logic.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.03.2019
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>users</title>
</head>
<body>
<table>
    <tr>
        <form method='get' action='${pageContext.request.contextPath}/create'>
            <button type='submit'>create user</button>
        </form>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td>
            <c:out value="${user}"></c:out>
        </td>
        <td>
            <form method='get' action='${pageContext.request.contextPath}/update'>
                <input type='hidden' name='id' value='${user.id}'/>
                <input type='hidden' name='name' value='${user.name}'/>
                <input type='hidden' name='login' value='${user.login}'/>
                <input type='hidden' name='email' value='${user.email}'/>
                <button type='submit'>update</button>
            </form>
        </td>
        <td>
            <form method='post' action='${pageContext.request.contextPath}/'>
                <input type='hidden' name='id' value='${user.id}'/>
                <button type='submit'>delete</button>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
