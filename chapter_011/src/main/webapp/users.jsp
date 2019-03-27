<%@ page import="ru.job4j.servlets.users.logic.ValidateService" %>
<%@ page import="ru.job4j.servlets.users.logic.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.03.2019
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>users</title>
</head>
<body>
<table>
    <tr>
        <form method='get' action='<%=request.getContextPath()%>/index.jsp'>
            <button type='submit'>create user</button>
        </form>
    </tr>
    <%for (User user : ValidateService.getInstance().show()) {%>
    <tr>
        <td>
        <%=user%>
        </td>
        <td>
            <form method='post' action='<%=request.getContextPath()%>/update.jsp'>
                <input type='hidden' name='id' value='<%=user.getId()%>'/>
                <input type='hidden' name='name' value='<%=user.getName()%>'/>
                <input type='hidden' name='login' value='<%=user.getLogin()%>'/>
                <input type='hidden' name='email' value='<%=user.getEmail()%>'/>
                <button type='submit'>update</button>
            </form>
        </td>
        <td>
            <form method='post' action='<%=request.getContextPath()%>/user'>
                <input type='hidden' name='id' value='<%=user.getId()%>'/>
                <button type='submit'>delete</button>
            </form>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
