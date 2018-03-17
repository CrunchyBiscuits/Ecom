package com.ecom.mapper;

import com.ecom.beans.Product;
import com.ecom.beans.ProductPicutre;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductMapper{
    @Select("select * from product_picture where product_id = #{product_id}")
    List<ProductPicutre> getProductPictures(int product_id);

    @Select("select * from product_picture where product_id = #{product_id} and sequence = #{sequence}")
    ProductPicutre getProductPicture(ProductPicutre productPicutre);

    @Select("select * from product where product_id = #{product_id}")
    Product getProduct(int product_id);

    @Select("select * from product")
    List<Product> getAllProducts();

    @Select("select last_insert_id()")
    int getLastInsertID();

    @Insert("insert into product(product_name,unit_price,details,add_date,stock,sales,status)" +
            "values(#{product_name},#{unit_price},#{details},#{add_date},#{stock},#{sales},#{status}) ")
    int insertProduct(Product product);

    @Insert("insert into product_picture(product_id,file,sequence) values(#{product_id},#{file},#{sequence})")
    int insertProductPicture(ProductPicutre productPicutre);

    @Delete("delete from product where product_id = #{product_id}")
    int deleteProduct(int product_id);

    @Update("update product set product_name=#{product_name},unit_price=#{unit_price} where product_id=#{product_id}")
    int updateProduct(Product product);
}
