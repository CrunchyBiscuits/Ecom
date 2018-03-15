package com.ecom.dao;

import com.ecom.beans.Product;
import com.ecom.mapper.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ProductOperator {

    private Product product;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    //构造函数初始化数据库连接，获取需要操作的user
    public ProductOperator(Product product){
        try{
            sqlSessionFactory = DatabaseFactory.getFactory();
            session = sqlSessionFactory.openSession();
            this.product = product;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //插入商品在ProfileOperator

    //删除商品
    public boolean deleteProduct(){
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        //通过SQL语句的返回值，判断是否删除成功
        int result = mapper.deleteProduct(product.getProduct_id());
        //确认执行，没有commit这一句，数据库不会更新
        session.commit();
        //判断结果
        if (result == 0)return false;
        return true;
    }

    //新增商品
    public boolean registerUser(){
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        //通过SQL语句的返回值，判断是否插入成功
        int result = mapper.insertProduct(product);
        //确认执行，没有commit这一句，数据库不会更新
        session.commit();
        //判断结果
        if (result == 0)return false;
        return true;
    }

    /*
    //插入商品图片
    public boolean insertProductPictures(){
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        //通过SQL语句的返回值，判断是否插入成功
        int result = mapper.insertProductPicture(product);

        //确认执行，没有commit这一句，数据库不会更新
        session.commit();
        if (result == 0) return false;
        return true;
    }*/

    //更新商品信息
    public boolean updateProduct(){
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        //通过SQL语句的返回值，判断是否更新成功
        int result = mapper.updateProduct(product);
        //确认执行，没有commit这一句，数据库不会更新
        session.commit();
        if (result == 0) return false;
        return true;
    }

    //获取商品信息
    public Product selectProduct(){
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        return mapper.getProduct(product.getProduct_id());
    }


    //finalize函数处理数据库的关闭
    protected void finalize() throws java.lang.Throwable{
        super.finalize();
        session.close();
    }
}
