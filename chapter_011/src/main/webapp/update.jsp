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
<form method='post' action='<%=request.getContextPath()%>/update'>
    <fieldset>
        <legend>edit</legend>
        <p>id : <input type='text' name='id' value='<%=request.getParameter("id")%>' readonly/></p>
        <p>name : <input type='text' name='name' value='<%=request.getParameter("name")%>'/></p>
        <p>login : <input type='text' name='login' value='<%=request.getParameter("login")%>'/></p>
        <p>email : <input type='text' name='email' value='<%=request.getParameter("email")%>'/></p>
    </fieldset>
    <p><input type="submit" value="update"></p>
</form>
</body>
</html>
