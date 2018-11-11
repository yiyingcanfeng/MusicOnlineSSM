package com.zxy.service;

import com.zxy.dao.UserMapper;
import com.zxy.model.LoginFromBean;
import com.zxy.model.RegisterFormBean;
import com.zxy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;


@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private User user;
    
    @Resource
    private UserMapper userMapper;
    
    @Override
    public boolean login(LoginFromBean loginFromBean) {
        boolean flag=true;
        user.setName(loginFromBean.getName());
        user.setPassword(loginFromBean.getPassword());
        
        if (!loginFromBean.getCode().toLowerCase().equals(loginFromBean.getRightCode())){
            loginFromBean.setMsg("验证码错误");
            flag=false;
        }else if (userMapper.findUserByName(user)!=1){
            loginFromBean.setMsg("用户名不存在或密码错误！");
            flag=false;
        }
        return flag;
    }
    
    @Override
    public boolean register(RegisterFormBean registerFormBean) {
        HashMap<String,String> msg=new HashMap<>();
        boolean flag=true;
        user.setName(registerFormBean.getName());
        user.setPassword(registerFormBean.getPassword());
        user.setEmail(registerFormBean.getEmail());
        if (userMapper.isExist(user)==1){
            msg.put("name","用户名已存在！");
            flag=false;
        }
        if (registerFormBean.getName()==null||registerFormBean.getName().trim().equals("")){
            msg.put("name","请输入用户名！");
            flag=false;
        }else if (registerFormBean.getName().length()>16 || registerFormBean.getName().length()<2){
            msg.put("name","用户名格式不正确！");
            flag=false;
        }
        if (registerFormBean.getPassword()==null||registerFormBean.getPassword().trim().equals("")){
            msg.put("password","请输入密码！");
            flag=false;
        } else if (registerFormBean.getPassword().length()>16 || registerFormBean.getPassword().length()<6){
            msg.put("password","密码格式不正确！");
            flag=false;
        }
        if (registerFormBean.getPassword2()==null||registerFormBean.getPassword2().trim().equals("")){
            msg.put("password2","请再次输入密码！");
            flag=false;
        } else if (!registerFormBean.getPassword().equals(registerFormBean.getPassword2())){
            msg.put("password2","两次密码不一致！");
            flag=false;
        }
        if (registerFormBean.getEmail()==null||registerFormBean.getEmail().trim().equals("")){
            msg.put("email","请输入邮箱！");
            flag=false;
        }else if (!registerFormBean.getEmail().matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")){
            msg.put("email","邮箱格式不正确！");
            flag=false;
        }
        registerFormBean.setMsg(msg);
        if (flag){
            userMapper.insert(user);
        }
        return flag;
    }
    
    @Override
    public boolean isExist(User user) {
        boolean flag;
        flag = userMapper.isExist(user) == 1;
        return flag;
    }
    
    @Override
    public Integer checkPermission(User user) {
        return userMapper.checkPermission(user);
    }
}
