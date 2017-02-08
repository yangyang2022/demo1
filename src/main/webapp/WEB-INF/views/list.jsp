<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List Emps</title>
    <%-- 如何把一个get请求转变为一个post请求, 再用spring将post请求转变为delete请求--%>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                $("form").attr("action",href).submit()
                return false;
            });
        })
    </script>
</head>
<body>
    <form action="" method="post">
        <input type="hidden" name="_method" value="delete">
    </form>
    <c:if test="${empty requestScope.emps}">
        没有任何员工信息
    </c:if>
    <c:if test="${!empty requestScope.emps}">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Birth</th>
                <th>Salary</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${requestScope.emps}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender == "0" ? "Female" : "Male"}</td>
                    <td>${emp.department.departmentName}</td>
                    <td>${emp.birth == null ? "空" : emp.birth}</td>
                    <td>${emp.salary}</td>
                    <td><a href="emp/${emp.id}">Edit</a></td>
                    <td><a class="delete" href="emp/${emp.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br>
    <a href="/emp">add employee</a>
</body>
</html>
