<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.03.2019
  Time: 1:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>edit</title>
</head>
<body>
<form method='post' action='${pageContext.request.contextPath}/update'>
    <fieldset>
        <legend>edit</legend>
        <p>id : <input type='text' name='id' value='${param.id}' readonly/></p>
        <p>name : <input type='text' name='name' value='${param.name}'/></p>
        <p>login : <input type='text' name='login' value='${param.login}'/></p>
        <p>email : <input type='text' name='email' value='${param.email}'/></p>
    </fieldset>
    <p><input type="submit" value="update"></p>
</form>
</body>
</html>
