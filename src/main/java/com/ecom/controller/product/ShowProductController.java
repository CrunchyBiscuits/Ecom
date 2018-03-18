package com.ecom.controller.product;

import com.ecom.beans.Product;
import com.ecom.beans.ProductPicutre;
import com.ecom.beans.User;
import com.ecom.dao.ProductOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
public class ShowProductController {

    @RequestMapping(value = "/productimage/{product_id}/{sequence}",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView image(@PathVariable int product_id, @PathVariable int sequence, HttpServletResponse response){
        try {
            Product product = new Product();
            product.setProduct_id(product_id);

            ProductOperator operator = new ProductOperator(product);

            ProductPicutre productPicutre = operator.selectProductPicture(sequence);
            //获取图片字节数据

            byte[] imgByte = productPicutre.getFile();
            if (imgByte.length!=0) {
                response.setContentType("image/jpeg");
                OutputStream outputStream = response.getOutputStream();

                outputStream.write(imgByte);
                outputStream.flush();
                outputStream.close();}
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("productimage");
    }

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/showproductprofile",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView showprofile(@RequestParam String product_id, HttpServletRequest request) {
        Product product = new Product();
        product.setProduct_id(Integer.parseInt(product_id));
        ProductOperator operator = new ProductOperator(product);
        int size = operator.getProductPictures().size();
        request.setAttribute("product_id",product.getProduct_id());
        request.setAttribute("size",size);
        return new ModelAndView("showProductProfile");
    }
}
