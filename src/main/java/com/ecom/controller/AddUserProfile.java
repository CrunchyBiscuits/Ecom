package com.ecom.controller;

import com.ecom.dao.ProfileOperator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@Controller
public class AddUserProfile {

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/profile",method= RequestMethod.POST)
    public ModelAndView addProfile(Model model,HttpServletRequest request) {

        ProfileOperator operator = new ProfileOperator();
        //System.out.println(user.getProfile());
        String profileMessage="";
        //添加头像
        if (operator.insertUserProfile(request)) {
            profileMessage = "Insert Profile Success";
        }else {
            profileMessage = "Insert Profile Failed";
        }
        System.out.println(profileMessage);
        return new ModelAndView("resultpage", "message", profileMessage);
    }

}
