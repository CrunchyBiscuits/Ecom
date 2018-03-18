package com.ecom.controller.product;

import com.ecom.dao.ProfileOperator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddProductController {

    @RequestMapping(value="/addproduct",method= RequestMethod.POST)
    public ModelAndView addProfile( HttpServletRequest request) {
        try{
            ProfileOperator operator = new ProfileOperator();
            String profileMessage="";
            request.setCharacterEncoding("UTF-8");
            if (operator.addProductAndPicture(request)) {
                profileMessage = "Insert Product Success";
            }else {
                profileMessage = "Insert Product Failed";
            }
            System.out.println(profileMessage);
            return new ModelAndView("resultpage", "message", profileMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
}

