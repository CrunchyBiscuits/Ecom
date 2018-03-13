package com.ecom.controller;

import com.ecom.beans.User;
import com.ecom.dao.UserOperator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
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

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/showprofile",method={RequestMethod.GET,RequestMethod.POST})
    public void login(Model model, HttpServletResponse response)throws IOException {

        //从Model类中获取名为"user"的数据
        User user = (User)model.asMap().get("user");

        //提示信息
        String showProfileMessage = " ";

        //UserOperator对user对象进行基础操作
        UserOperator operator = new UserOperator(user);
        //获取图片字节数据
        byte[] imgByte = operator.selectUser().getProfile();
        if (imgByte.length!=0) {
            //字节数据转换成流
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgByte);
            //字节数据暂存入缓存
            BufferedImage img = ImageIO.read(byteArrayInputStream);
            response.setContentType("image/jpeg");
            OutputStream outputStream = response.getOutputStream();

            ImageIO.write(img, "JPEG", outputStream);
            outputStream.flush();
            outputStream.close();

            showProfileMessage = "Show Success";
        }else {
            showProfileMessage = "Show Failed";
        }
        System.out.println(showProfileMessage);

    }
}
