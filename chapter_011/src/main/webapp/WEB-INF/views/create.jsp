<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.03.2019
  Time: 2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>create</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript"><%@include file="/WEB-INF/script/location.js"%></script>
    <script type="text/javascript"><%@include file="/WEB-INF/script/validate.js"%></script>
    <style type="text/css">
        body {
            background-color:#C0C0C0;
        }
        .container { margin-top:20px; }
    </style>
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="mainform" method='post' action='${pageContext.request.contextPath}/create' onsubmit="return validate()">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="name" placeholder="Enter your name" name="name">
            </div>
        </div>
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
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-2">
                <input type="email" class="form-control" id="email" placeholder="Enter your email" name="email">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Country:</label>
            <div class="col-sm-2">
                <select class="form-control" id="country" name="country" onchange="change()" required>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="city">City:</label>
            <div class="col-sm-2">
                <select class="form-control" id="city" name="city">
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default" id="button">create</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
