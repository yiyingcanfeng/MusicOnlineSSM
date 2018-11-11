package com.zxy.model;


import com.zxy.dao.UserMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class RegisterFormBean {
    private String name;
    private String password;
    private String password2;
    private String email;
    private Map<String,String> msg=new HashMap<>();
    
    @Resource
    private UserMapper userMapper;
    
    public boolean validate(){
        boolean flag=true;
        if (userMapper.isExist(new User(name))==1){
            msg.put("name","用户名已存在！");
            flag=false;
        }
        if (name==null||name.trim().equals("")){
            msg.put("name","请输入用户名！");
            flag=false;
        }else if (name.length()>16 || name.length()<2){
            msg.put("name","用户名格式不正确！");
            flag=false;
        }
        if (password==null||password.trim().equals("")){
            msg.put("password","请输入密码！");
            flag=false;
        } else if (password.length()>16 || password.length()<6){
            msg.put("password","密码格式不正确！");
            flag=false;
        }
        if (password2==null||password.trim().equals("")){
            msg.put("password2","请再次输入密码！");
            flag=false;
        } else if (!password.equals(password2)){
            msg.put("password2","两次密码不一致！");
            flag=false;
        }
        if (email==null||email.trim().equals("")){
            msg.put("email","请输入邮箱！");
            flag=false;
        }else if (!email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")){
            msg.put("email","邮箱格式不正确！");
            flag=false;
        }
        return flag;
    }
    
    public Map<String, String> getMsg() {
        return msg;
    }
    
    public void setMsg(Map<String, String> msg) {
        this.msg = msg;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword2() {
        return password2;
    }
    
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
   
    @Override
    public String toString() {
        return "RegisterFormBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", email='" + email + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
