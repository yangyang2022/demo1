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
    <h1>This page is success!!!</h1>

    time:${requestScope.time}<br/>
    names: ${requestScope.names}<br/>
    request User: ${requestScope.user}<br/>
    session User: ${sessionScope.user}<br/>

    request school: ${requestScope.school}<br/>
    session school: ${sessionScope.school}<br/>

    abc user: ${requestScope.abc}<br/>
    user user: ${requestScope.user}<br/>

    <fmt:message key="i18n.username" /><br/>
    <fmt:message key="i18n.password"  />

</body>
</html>
