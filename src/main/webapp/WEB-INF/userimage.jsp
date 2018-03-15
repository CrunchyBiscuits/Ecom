<%@ page import="com.ecom.dao.UserOperator" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="com.ecom.beans.User" %>
<%
    String useremail = request.getParameter("email");

    User user = new User();
    user.setEmail(useremail);
    //UserOperator对user对象进行基础操作
    UserOperator operator = new UserOperator(user);
    //获取图片字节数据
    byte[] imgByte = operator.selectUser().getProfile();
    if (imgByte.length!=0) {
      /*  //字节数据转换成流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgByte);
        //字节数据暂存入缓存
        BufferedImage img = ImageIO.read(byteArrayInputStream);*/
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();

        outputStream.write(imgByte);
        outputStream.flush();
        outputStream.close();}
%>