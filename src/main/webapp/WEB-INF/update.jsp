<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Demo - Update User Info</title>
</head>
<body>
    <% String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; %>
    <form method="post" action="<%=basePath %>/update">
        Email:<input type="text" name="email"/>
        <br/>
        Name:<input type="text" name="name"/>
        <br/>
        Phone:<input type="text" name="phone"/>
        <br/>
        <input type="submit" value="submit"/>
    </form>
</body>
</html>
