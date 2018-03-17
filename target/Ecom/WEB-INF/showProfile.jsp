<%--
  Created by IntelliJ IDEA.
  User: ZerounZhang
  Date: 2018/3/13
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%String email = request.getParameter("email");%>
<table>
    <tr>
        <td>
            <img src="userimage?email=<%=email%>">
        </td>
    </tr>
</table>

</body>
</html>
