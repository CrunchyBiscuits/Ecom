package com.ecom.controller;

import com.ecom.beans.User;
import com.ecom.dao.UserOperator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserUpdateController {

    //通过Model类获取页面信息
    @ModelAttribute
    public void userModel(String name,String phone,  Model model){
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        model.addAttribute("user",user);
    }

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/update",method= RequestMethod.POST)
    public ModelAndView login(Model model) {

        //从Model类中获取名为"user"的数据
        User user = (User)model.asMap().get("user");
        String updateMessage="";
        UserOperator operator = new UserOperator(user);

        //更新
        if (operator.updateUser()) {
            updateMessage = "Update Success";
        }else {
            updateMessage = "Update Failed";
        }
        System.out.println(updateMessage);
        return new ModelAndView("update", "message", updateMessage);
    }

}
