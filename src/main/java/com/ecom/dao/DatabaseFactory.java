package com.ecom.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public  class DatabaseFactory {

    //公用配置文件，配置文位置件不能更改
    private static final String resource="mybatis-config.xml";

    public static SqlSessionFactory getFactory()throws Exception{
        InputStream inputStream =  Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
