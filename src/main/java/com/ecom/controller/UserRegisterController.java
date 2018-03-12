package com.ecom.controller;

import com.ecom.beans.User;
import com.ecom.dao.UserOperator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class UserRegisterController {

    //通过Model类获取页面信息
    @ModelAttribute
    public void userModel(User user,  Model model){
        model.addAttribute("user",user);
    }

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/register",method= RequestMethod.POST)
    public ModelAndView login(Model model) {

        //从Model类中获取名为"user"的数据
        User user = (User)model.asMap().get("user");
        String registerMessage="";
        UserOperator operator = new UserOperator(user);

        //注册
        if (operator.registerUser()) {
            registerMessage = "Register Success";
        }else {
            registerMessage = "Register Failed";
        }
        System.out.println(registerMessage);
        return new ModelAndView("register", "message", registerMessage);
    }

}
