<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.04.2019
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>enter</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="min-font-size: 24">
    <c:out value="${error}"/>
    </div>
</c:if>
<form method='post' action='${pageContext.request.contextPath}/authentication'>
    <fieldset>
        <legend>sign in</legend>
        <p>login : <input type='text' name='login'/></p>
        <p>password : <input type='password' name='password'/></p>
    </fieldset>
    <p><input type='submit' value='sign in'></p>
</form>
</br>
<form method='get' action='${pageContext.request.contextPath}/create'>
    <button type='submit'>sign up</button>
</form>
</body>
</html>
