<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Demo - Register</title>
</head>
<body>
<% String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; %>
    <form method="post" action="<%=basePath %>/register">
        Email:<input type="text" name="email"/>
        <br/>
        Name:<input type="text" name="name"/>
        <br/>
        Password:<input type="password" name="password"/>
        <br/>
        Actual Name:<input type="text" name="actual_name"/>
        <br/>
        Phone:<input type="text" name="phone"/>
        <br/>
        Gender:
        <input type="radio" name="gender" value="0" />Male
        <input type="radio" name="gender" value="1"/>Female
        <br/>
        <input type="submit" value="submit"/>
    </form>
</body>
</html>
