package com.ecom.controller.user;

import com.ecom.dao.UserOperator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecom.beans.User;

@Controller
public class LoginController {

    //通过Model类获取页面信息
    @ModelAttribute
    public void userModel(String email, String password,  Model model){
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);

        model.addAttribute("user",user);
    }

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(Model model) {

        //从Model类中获取名为"user"的数据
        User user = (User)model.asMap().get("user");

        //保留password数据用于检查
        String password = user.getPassword();

        //提示信息
        String loginMessage = " ";

        //UserOperator对user对象进行基础操作
        UserOperator operator = new UserOperator(user);
        //检查登录
        if (operator.checkLoginInfo(password)) {
            loginMessage = "Login Success";
        }else {
            loginMessage = "Login Failed";
        }
        System.out.println(loginMessage);
        return new ModelAndView("resultpage", "message", loginMessage);
    }
}
