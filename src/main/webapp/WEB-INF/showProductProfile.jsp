<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ecom.dao.ProductOperator" %>
<%@ page import="com.ecom.beans.Product" %><%--
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
<%
    int product_id = (int)request.getAttribute("product_id");
    int size = (int)request.getAttribute("size");
%>
<h2><%=product_id%></h2>
<h2><%=size%></h2>
<table>
    <%
        for (int i=0;i<size;i++)
        {
            String imgUrl = "productimage/"+product_id+"/"+i;
    %>
    <tr>
        <td>
            <img src="<%=imgUrl%>">
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
