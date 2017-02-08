<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <fmt:message key="i18n.username" />
    <br />

    <a href="i18n2">i18n2</a><br />

    <a href="i18n?locale=zh_CN">中文</a><br />
    <a href="i18n?locale=en_US">英文</a><br />

</body>
</html>
