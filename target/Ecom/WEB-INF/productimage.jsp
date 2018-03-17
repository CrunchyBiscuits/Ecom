<%@ page import="java.io.OutputStream" %>
<%@ page import="com.ecom.beans.ProductPicutre" %>
<%@ page import="com.ecom.dao.ProductOperator" %>
<%@ page import="com.ecom.beans.Product" %>
<%
    /*int product_id = (int)request.getAttribute("product_id");
    int sequence = (int)request.getAttribute("sequence");

    Product product = new Product();
    product.setProduct_id(product_id);

    ProductOperator operator = new ProductOperator(product);

    ProductPicutre productPicutre = operator.selectProductPicture(sequence);
    //获取图片字节数据

    byte[] imgByte = productPicutre.getFile();
    if (imgByte.length!=0) {
      /*  //字节数据转换成流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgByte);
        //字节数据暂存入缓存
        BufferedImage img = ImageIO.read(byteArrayInputStream);
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();

        outputStream.write(imgByte);
        outputStream.flush();
        outputStream.close();}*/
%>