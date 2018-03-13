package com.ecom.mapper;

import com.ecom.beans.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Blob;
import java.util.List;

public interface UserMapper {

    @Insert("insert into user(email,name,password,role,actual_name,phone,gender,status) " +
            "values(#{email},#{name},#{password},#{role},#{actual_name},#{phone},#{gender},#{status})")
    int insertUser(User user);

    @Insert("insert into user(profile) values(#{profile})")
    int insertUserProfile(String email, Blob profile);

    @Delete("delete from user where EMAIL=#{email}")
    int deleteUser(String email);

    @Update("update user set name=#{name},phone=#{phone} where email=#{email}")
    int updateUser(User user);

    @Select("select * from user where email=#{email}")
    User getUserByEmail(String email);

    @Select("select * from user")
    List<User> getUsers();
}
