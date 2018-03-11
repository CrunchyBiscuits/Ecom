package com.ecom.mapper;

import com.ecom.beans.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface UserMapper {

    @Insert("insert into user(EMAIL,NAME,PASSWORD，ROLE,ACTUAL_NAME,PHONE,GENDER,PAY_PASSWORD,STATUS) " +
            "values(#{email},#{name},#{password},#{role},#{actual_name},#{phone},#{gender},#{pay_password},#{status})")
    void insertUser(User user);

    @Delete("delete from user where EMAIL=#{email}")
    void deleteUser(String email);

    @Update("update user set NAME=#{name},PASSWORD=#{password} where EMAIL=#{email}")
    void updateUser(User user);

    @Select("select * from user where EMAIL=#{email}")
    User getUserByEmail(String email);

    @Select("select * from user")
    List<User> getUsers();
}
