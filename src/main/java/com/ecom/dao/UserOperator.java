package com.ecom.dao;


import com.ecom.beans.User;
import com.ecom.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserOperator {

    private User user;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    //构造函数初始化数据库连接，获取需要操作的user
    public UserOperator(User user){
        try{
            sqlSessionFactory = DatabaseFactory.getFactory();
            session = sqlSessionFactory.openSession();
            this.user = user;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查登录信息
    public boolean checkLoginInfo(String password){
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User loginUser = mapper.getUserByEmail(user.getEmail());

            System.out.println(loginUser.getPassword());
            if (loginUser==null||!password.equals(loginUser.getPassword())){
                return false;
            }else{
                return  true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //finalize函数处理数据库的关闭
    protected void finalize() throws java.lang.Throwable{
        super.finalize();
        session.commit();
        session.close();
    }
}
