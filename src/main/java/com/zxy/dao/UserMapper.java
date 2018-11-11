package com.zxy.dao;

import com.zxy.model.User;

public interface UserMapper {
    Integer findUserByName(User user);//登录验证
    Integer isExist(User user);//查询用户名是否已存在
    void insert(User user);//插入用户
    Integer checkPermission(User user);//查询用户权限
}
