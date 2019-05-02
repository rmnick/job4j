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
    <form class="form-horizontal" id="mainform" method='post' action='${pageContext.request.contextPath}/update' onsubmit="return validate()">
        <div class="form-group">
            <label class="control-label col-sm-2" for="id">Id:</label>
            <div class="col-sm-1">
                <input type="text" class="form-control" id="id" name="id" value='${param.id}' readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="name" name="name" value='${param.name}'>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="login" name="login" value='${param.login}'>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password:</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-2">
                <input type="email" class="form-control" id="email" name="email" value='${param.email}'>
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
                <button type="submit" class="btn btn-default" id="button">update</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
