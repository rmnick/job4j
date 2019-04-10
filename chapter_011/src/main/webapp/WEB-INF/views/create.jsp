<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.03.2019
  Time: 2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>create</title>
</head>
<body>
<form method='post' action='${pageContext.request.contextPath}/create'>
    <fieldset>
        <legend>create</legend>
        <p>name : <input type='text' name='name'/></p>
        <p>login : <input type='text' name='login'/></p>
        <p>password : <input type='password' name='password'/></p>
        <p>email : <input type='text' name='email'/></p>
    </fieldset>
    <p><input type='submit' value='create'></p>
</form>
</body>
</html>
