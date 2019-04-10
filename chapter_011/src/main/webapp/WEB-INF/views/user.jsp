<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.04.2019
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>user</title>
</head>
<body>
<table>
    <tr>
        <td>
            <c:out value="${user}"></c:out>
        </td>
        <td>
            <form method='get' action='${pageContext.request.contextPath}/update'>
                <input type='hidden' name='id' value='${user.id}'/>
                <input type='hidden' name='name' value='${user.name}'/>
                <input type='hidden' name='login' value='${user.login}'/>
                <input type='hidden' name='login' value='${user.login}'/>
                <input type='hidden' name='email' value='${user.email}'/>
                <button type='submit'>update</button>
            </form>
        </td>
        <td>
            <form method='post' action='${pageContext.request.contextPath}/user'>
                <input type='hidden' name='id' value='${user.id}'/>
                <button type='submit'>delete</button>
            </form>
        </td>
    </tr>
    <tr>
        <form method='get' action='${pageContext.request.contextPath}/authentication'>
            <button type='submit'>sign out</button>
        </form>
    </tr>
</table>
</body>
</html>
