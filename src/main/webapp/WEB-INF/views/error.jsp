<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2017/1/7
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>This page is error!!!</h1>

    <h1>Error:${ex} -- ${test} -- ${requestScope.exception}</h1>
</body>
</html>
