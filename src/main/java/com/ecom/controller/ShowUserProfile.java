package com.ecom.controller;

import com.ecom.beans.User;
import com.ecom.dao.UserOperator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class ShowUserProfile {
    //通过Model类获取页面信息
    @ModelAttribute
    public void userModel(String email,  Model model){
        User user = new User();
        user.setEmail(email);
        model.addAttribute("user",user);
    }

    @RequestMapping(value = "/userimage{email}",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView image(@PathVariable String email){

        return new ModelAndView("userimage");
    }

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/showprofile",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView showprofile(Model model,HttpServletRequest request) {
        User user = (User)model.asMap().get("user");
        request.setAttribute("email",user.getEmail());
        return new ModelAndView("resultpage");
    }
}
