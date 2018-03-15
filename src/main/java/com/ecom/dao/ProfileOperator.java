package com.ecom.dao;

import com.ecom.beans.Product;
import com.ecom.beans.ProductPicutre;
import com.ecom.beans.User;
import com.ecom.mapper.ProductMapper;
import com.ecom.mapper.UserMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileOperator{

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    //构造函数，初始化mybatis信息
    public ProfileOperator(){
        try{
            sqlSessionFactory = DatabaseFactory.getFactory();
            session = sqlSessionFactory.openSession();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从request中获取user相应的字段信息和图片信息
    public boolean insertUserProfile(HttpServletRequest request){
        try{
        //ArrayList用于存储非文件类型的其他数据，使用Map类，String用作key，object当value方便进行转换
        ArrayList<Map<String ,Object>> itemlist = new ArrayList<Map<String, Object>>();
        //FileInputStream获取传来文件的输入流
        FileInputStream fis = null;
        //字节数组用于存储输入流
        byte[] imgByte = null;

        //apache common file upload 库的方法，获取相应的图片数据,需要在dependency中添加org.apache.commons.fileupload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> parseRequest = upload.parseRequest(request);

        if (parseRequest!= null){
            for (FileItem item : parseRequest){
                //判断是否为普通字段数据
                if (item.isFormField()){
                    Map<String, Object> rowData = new HashMap<String, Object>();
                    rowData.put(item.getFieldName(), new String(item.get(),"UTF-8"));
                    itemlist.add(rowData);
                }else {
                    //是文件上传，获取文件的名字
                    // fNameString = item.getFieldName();
                    fis = (FileInputStream) item.getInputStream();
                    //获取流大小，确定byte数组大小
                    imgByte = new byte[fis.available()];
                    //输入流
                    fis.read(imgByte);
                }
            }
        }

            //获取存储在itemlist中的数据，数据顺序和jsp中各控件顺序相同
            String email = itemlist.get(0).get("email").toString();
            User user = new User();

            user.setEmail(email);
            user.setProfile(imgByte);

            UserMapper mapper = session.getMapper(UserMapper.class);
            //通过SQL语句的返回值，判断是否插入成功
            int result = mapper.insertUserProfile(user);

            //确认执行，没有commit这一句，数据库不会更新
            session.commit();
            if (result>0) return true;
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //插入商品的图片
    public boolean addProductAndPicture(HttpServletRequest request){
        try{
            //ArrayList用于存储非文件类型的其他数据，使用Map类，String用作key，object当value方便进行转换
            ArrayList<Map<String ,Object>> itemlist = new ArrayList<Map<String, Object>>();

            //另一个ArrayList用于对多个图片文件进行存储，方便添加
            ArrayList<Map<String,byte[]>> picturelist = new ArrayList<Map<String, byte[]>>();

            //FileInputStream获取传来文件的输入流
            FileInputStream fis = null;
            //字节数组用于存储输入流
            byte[] imgByte = null;

            //apache common file upload 库的方法，获取相应的图片数据,需要在dependency中添加org.apache.commons.fileupload
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> parseRequest = upload.parseRequest(request);

            if (parseRequest!= null){
                for (FileItem item : parseRequest){
                    //添加计数器 为图片计数，方便之后通过顺序调取
                    int imgNum = 0;
                    //判断是否为普通字段数据
                    if (item.isFormField()){
                        Map<String, Object> rowData = new HashMap<String, Object>();
                        rowData.put(item.getFieldName(), new String(item.get(),"UTF-8"));
                        itemlist.add(rowData);
                    }else {
                        //是文件上传，获取文件的名字
                        fis = (FileInputStream) item.getInputStream();
                        //获取流大小，确定byte数组大小
                        imgByte = new byte[fis.available()];
                        //输入流
                        fis.read(imgByte);

                        //存储进arraylist中
                        Map<String, byte[]> imgData = new HashMap<String, byte[]>();
                        imgData.put(imgNum+"", imgByte);
                        picturelist.add(imgData);
                        imgNum++;
                    }
                }
            }

            //获取存储在itemlist中的数据，数据顺序和jsp中各控件顺序相同，并赋值给product
            //只有先插入product才能获取product_id，才能插入图片
            int shop_id = Integer.parseInt(itemlist.get(0).get("shop_id").toString());
            String product_name = itemlist.get(1).get("product_name").toString();
            int category_id = Integer.parseInt(itemlist.get(2).get("category_id").toString());
            float unit_price = Float.parseFloat(itemlist.get(3).get("unit_price").toString());
            String details = itemlist.get(4).get("details").toString();
            String add_date = itemlist.get(5).get("add_date").toString();
            int stock = Integer.parseInt(itemlist.get(6).get("stock").toString());
            int sales = Integer.parseInt(itemlist.get(7).get("sales").toString());
            int status = Integer.parseInt(itemlist.get(8).get("status").toString());

            Product product = new Product();
            product.setShop_id(shop_id);
            product.setProduct_name(product_name);
            product.setCategory_id(category_id);
            product.setUnit_price(unit_price);
            product.setDetails(details);
            product.setDatetime(add_date);
            product.setStock(stock);
            product.setSales(sales);
            product.setStatus(status);

            //插入product信息
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            productMapper.insertProduct(product);

            //获取product_id
            int product_id = productMapper.getLastInsertID();

            //创建productpicture的model类用于插入数据库
            ProductPicutre productPicutre = new ProductPicutre();
            //创建计数器用于获取
            int imgNum = 0;
            //布尔判断，每一项是否插入成功
            boolean check = false;


            for(Map<String, byte[]> img : picturelist){
                productPicutre.setProduct_id(product_id);
                productPicutre.setFile(img.get(imgNum));
                productPicutre.setSequence(imgNum);
                if(productMapper.insertProductPicture(productPicutre)>0){
                    check = true;
                }else{
                    check = false;
                    return check;
                }
                imgNum++;
            }
            return check;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
