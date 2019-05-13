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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            background-color:#C0C0C0;
        }
        .container { margin-top:20px; }
    </style>
</head>
<body>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Login</th>
                <th scope="col">Email</th>
                <th scope="col">Country</th>
                <th scope="col">City</th>
                <th scope="col">Date</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.country}"></c:out></td>
                <td><c:out value="${user.city}"></c:out></td>
                <td><c:out value="${user.date}"></c:out></td>
                <td>
                    <form method='get' action='${pageContext.request.contextPath}/update'>
                        <input type='hidden' name='id' value='${user.id}'/>
                        <input type='hidden' name='name' value='${user.name}'/>
                        <input type='hidden' name='login' value='${user.login}'/>
                        <input type='hidden' name='login' value='${user.country}'/>
                        <input type='hidden' name='login' value='${user.city}'/>
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
            </tbody>
        </table>
        <form method='get' action='${pageContext.request.contextPath}/authentication'>
            <button type='submit'>sign out</button>
        </form>
    </div>
</body>
</html>
