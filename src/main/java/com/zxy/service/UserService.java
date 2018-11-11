package com.zxy.service;

import com.zxy.model.LoginFromBean;
import com.zxy.model.RegisterFormBean;
import com.zxy.model.User;


public interface UserService {
    
    boolean login(LoginFromBean loginFromBean);//登录验证
    boolean isExist(User user);//查询用户名是否已存在
    boolean register(RegisterFormBean registerFormBean);//插入用户
    Integer checkPermission(User user);//查询用户权限
}
