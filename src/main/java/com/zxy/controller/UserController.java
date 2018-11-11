package com.zxy.controller;

import cn.dsna.util.images.ValidateCode;
import com.google.gson.Gson;
import com.zxy.model.LoginFromBean;
import com.zxy.model.RegisterFormBean;
import com.zxy.model.User;
import com.zxy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("user")
public class UserController {
    
   
    @Resource
    private UserService userService;
    @Resource
    private User user;
    @Resource
    private Gson gson;
    
    @RequestMapping("admin")
    public String index(){
        return "admin";
    }
    
    
    @RequestMapping("loginUser")
    @ResponseBody
    public User loginUser(HttpSession session){
        return (User) session.getAttribute("user");
    }
    
    @RequestMapping("login")
    @ResponseBody
    public Object login(@RequestBody LoginFromBean loginFromBean,HttpSession session) {
        String rightCode= (String) session.getAttribute("code");
        HashMap<String, Object> map = new HashMap<>();
        loginFromBean.setRightCode(rightCode);
        boolean validate=userService.login(loginFromBean);
        
        if (validate) {
            user.setName(loginFromBean.getName());
            user.setPassword(loginFromBean.getPassword());
            map.put("result", true);
            map.put("msg", "登陆成功");
            map.put("permission", +userService.checkPermission(user));
            session.setAttribute("user",user);
        }else{
            map.put("result", false);
            map.put("msg", loginFromBean.getMsg());
        }
        return map;
    }
    
    @RequestMapping("register")
    @ResponseBody
    public Object register(@RequestBody RegisterFormBean registerFormBean) {
        HashMap<String, Object> map = new HashMap<>();
        boolean validate=userService.register(registerFormBean);
        if (validate) {
            map.put("result", true);
            map.put("msg", "注册成功");
            return map;
        }else {
            return registerFormBean.getMsg();
        }
    }
    
    @RequestMapping("userIsExist")
    @ResponseBody
    public Object userIsExist(@RequestBody User user) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        String msg;
        boolean flag = userService.isExist(user);
        if (flag) {
            map.put("result", true);
        }else {
            map.put("result", false);
        }
        return map;
    }
    
    
    @RequestMapping("validateCode")
    public void validateCode(HttpServletResponse response,HttpSession session){
        try {
            response.setHeader("pragma", "no-cache");
            response.setHeader("cache-control", "no-cache");
            response.setHeader("expires", "0");
            ValidateCode vc= new ValidateCode(120,36,4,4);
            vc.write(response.getOutputStream());
            String code =vc.getCode().toLowerCase();
            session.setAttribute("code",code);
            String a= (String) session.getAttribute("code");
            System.out.println(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
