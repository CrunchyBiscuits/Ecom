<%--
  Created by IntelliJ IDEA.
  User: ZerounZhang
  Date: 2018/3/15
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%int product_id = Integer.parseInt(request.getParameter("product_id"));
int totalNum = Integer.parseInt(request.getParameter("totalNum"));%>
<table>
    <%
        for (int i=0;i<totalNum;i++)
        {
    %>
    <tr>
        <td>
            <img src="productimage?product_id=<%=product_id%>&sequence<%=i%>">
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
