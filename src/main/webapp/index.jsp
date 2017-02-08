<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#testJson").click(function () {
                var url = this.href
                var args = {}
                $.post(url,args,function (data) {
                    for(var i=0;i<data.length;i++){
                        var id = data[i].id
                        var lastName = data[i].lastName
                        alert(id+" : "+lastName)
                    }
                })
                return false
            })
        })
    </script>
</head>
<body>

<%-- 模拟修改操作
原始数据:User(1,"Tom","123123",12,"Tom@qq.com") 要求密码不能不能修改 直接在表单修改
--%>

<form action="testModelAttribute" method="post">
    <input type="hidden" name="id" value="1">
    username:　<input type="text" name="username" value="tom"><br/>
    password:　<input type="text" name="password" value="123123" disabled><br/>
    age:　<input type="text" name="age" value="12"><br/>
    email:　<input type="email" name="email" value="tom@qq.com"><br/>
    <input type="submit" value="submit" />
</form>

    <br/>
    <form action="springmvc/testpojo" method="post">
        username: <input type="text" name="username"><br/>
        password: <input type="password" name="password"> <br/>
        age: <input type="text" name="age"><br/>
        email : <input type="email" name="email"><br/>
        city: <input type="city" name="address.city"><br/>
        province: <input type="city" name="address.province"><br/>
        <input type="submit" value="submit">
    </form>

    <br/>
    <a href="requestParam?username=yangyang&age=11">requestParam</a><br/>

    <a href="rest/get">get method</a><br/>

    <form action="rest/post" method="post">
        <input type="submit" value="submit"/>
    </form>

    <form action="rest/put" method="post">
        <input type="hidden" name="_method" value="put">
        <input type="submit" value="put"/>
    </form>
    <form action="rest/delete" method="post">
        <input type="hidden" name="_method" value="delete">
        <input type="submit" value="delete"/>
    </form>

    <a href="params?username=yangyang&age=11">testParams</a>
    <br/>
    <a href="testJson" id="testJson">Test Json!</a><br/>

<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"><br />
    Desc: <input type="text" name="desc"><br />
    <input type="submit" value="suubmit">
</form>

<br/>
<a href="ResponseEntity">ResponseEntity</a><br />
<form action="testFileUpoad" method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"><br />
    Desc: <input type="text" name="desc"><br />
    <input type="submit" value="suubmit">
</form>
</body>
</html>
