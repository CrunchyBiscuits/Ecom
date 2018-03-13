package com.ecom.controller;

import com.ecom.beans.User;
import com.ecom.dao.UserOperator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AddUserProfile {

    //通过Model类获取页面信息
    @ModelAttribute
    public void userModel(HttpServletRequest request,  Model model){
        try{
            //ArrayList用于存储非文件类型的其他数据，使用Map类，String用作key，object当value方便进行转换
            ArrayList<Map<String ,Object>> itemlist = new ArrayList<Map<String, Object>>();
            //FileInputStream获取传来文件的输入流
            FileInputStream fis = null;
            //字节数组用于存储输入流
            byte[] imgByte = null;

            //apache common file upload 库的方法，获取相应的图片数据,需要在dependency中添加org.apache.commons.fileupload
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> parseRequest = upload.parseRequest(request);

            if (parseRequest!= null){
                for (FileItem item : parseRequest){
                    //判断是否为普通字段数据
                    if (item.isFormField()){
                        Map<String, Object> rowData = new HashMap<String, Object>();
                        rowData.put(item.getFieldName(), new String(item.get(),"UTF-8"));
                        itemlist.add(rowData);
                    }else {
                        //是文件上传，获取文件的名字
                        String fNameString = item.getFieldName();
                        fis = (FileInputStream) item.getInputStream();
                        //获取流大小，确定byte数组大小
                        imgByte = new byte[fis.available()];
                        //获取流的内容
                        fis.read(imgByte);
                    }
                }
            }

            //获取存储在itemlist中的数据，数据顺序和jsp中各控件顺序相同
            String email = itemlist.get(0).get("email").toString();
            User user = new User();

            user.setEmail(email);
            user.setProfile(imgByte);

            model.addAttribute("user",user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //返回ModelAndView进行基础页面输出
    @RequestMapping(value="/profile",method= RequestMethod.POST)
    public ModelAndView addProfile(Model model) {
        User user = (User)model.asMap().get("user");
        //System.out.println(user.getProfile());
        String profileMessage="";
        UserOperator operator = new UserOperator(user);


        //添加头像
        if (operator.insertUserProfile()) {
            profileMessage = "Insert Profile Success";
        }else {
            profileMessage = "Insert Profile Failed";
        }
        System.out.println(profileMessage);
        return new ModelAndView("addProfile", "message", profileMessage);
    }

}
