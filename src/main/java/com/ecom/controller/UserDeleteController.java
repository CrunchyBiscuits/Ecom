package com.ecom.controller;

import com.ecom.beans.User;
import com.ecom.dao.UserOperator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class UserDeleteController {

    //通过Model类获取页面信息
    @ModelAttribute
    public void userModel(String email,  Model model){
        User user = new User();
        user.setEmail(email);
        model.addAttribute("user",user);
    }

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/delete",method= RequestMethod.POST)
    public ModelAndView login(Model model) {

        //从Model类中获取名为"user"的数据
        User user = (User)model.asMap().get("user");
        String deleteMessage="";
        UserOperator operator = new UserOperator(user);

        //删除
        if (operator.deleteUser()) {
            deleteMessage = "Delete Success";
        }else {
            deleteMessage = "Delete Failed";
        }
        System.out.println(deleteMessage);
        return new ModelAndView("delete", "message", deleteMessage);
    }

}
