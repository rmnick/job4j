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
<c:if test="${error != ''}">
    <div style="min-font-size: 24">
    <c:out value="${error}"/>
    </div>
</c:if>
<div class="container">
    <form class="form-horizontal" id="signin" method='post' action='${pageContext.request.contextPath}/authentication'>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="login" placeholder="Enter your login" name="login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password:</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" placeholder="Enter your password" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default" id="buttonin">sign in</button>
            </div>
        </div>
    </form>
    <form class="form-horizontal" id="signup" method='get' action='${pageContext.request.contextPath}/create'>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default" id="buttonup">sign up</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
