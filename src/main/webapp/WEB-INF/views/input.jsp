<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add Employee</title>
</head>
<body>
    <%-- string -> employee --%>
    <form action="testConverter" method="post">
        <%-- lastName-email-gender-department.id 例如:GG-gg@qq.com-0-105 --%>
        Employee: <input type="text" name="employee" />
        <input type="submit" value="submit">
    </form>

<br/><br/>

    <%--<form:errors path="*"></form:errors><br/>--%>

    <%-- 使用from标签,不仅可以快速开发表单页面,也可以表单回显--%>
    <form:form action="/emp" method="post" modelAttribute="employee">
        <%-- path is html name--%>
        <c:if test="${employee.id ==null}">
            LastName: <form:input path="lastName" />
            <form:errors path="lastName" /><br/>
        </c:if>

        <c:if test="${employee.id != null}">
            LastName: <form:input path="lastName" disabled="true"/>
            <form:errors path="lastName" />
            <br/>
            <form:hidden path="id" />
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        Email: <form:input path="email" />
        <form:errors path="email" />
        <br/>
        <%
            Map<String,String> genders = new HashMap<>();
            genders.put("1","Female");
            genders.put("0","Male");
            request.setAttribute("genders",genders);
        %>
        Gender: <br/><form:radiobuttons path="gender" items="${genders}" delimiter="<br/>"/>
        <form:errors path="gender" />
        <br/>
        <%-- 1: 数据类型转换(string -> LocalDate) 2: 数据类型格式化问题 3: 数据校验问题 --%>
        Birth: <form:input path="birth" /><form:errors path="birth" /><br/>
        Salary: <form:input path="salary" /><form:errors path="salary" /><br/>
        Department: <form:select path="department.id" items="${deps}" itemLabel="departmentName" itemValue="id" /><br/>
        <input type="submit" value="submit">
    </form:form>
</body>
</html>
