<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.03.2019
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>fault</title>
    <style type="text/css">
        body {
            background-color:#C0C0C0;
        }
        .container {
            margin-top:20px;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="container">
<c:out value='${requestScope.message}'/>
</div>
</body>
</html>
