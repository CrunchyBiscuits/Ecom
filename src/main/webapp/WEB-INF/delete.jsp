<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Demo - Delete User</title>
</head>
<body>
    <% String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; %>
    <form method="post" action="<%=basePath %>/delete">
        Input the Email for the account you want to delete:
        <br/>
        <input type="text" name="email"/>
        <br/>
        <input type="submit" value="submit"/>
    </form>
</body>
</html>
