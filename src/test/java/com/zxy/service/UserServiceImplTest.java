package com.zxy.service;

import com.zxy.dao.UserMapper;
import com.zxy.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class UserServiceImplTest {
    
    @Resource
    private UserMapper userMapper;
    @Test
    public void isExist() {
    
        User user = new User();
        user.setName("admin");
        Integer exist = userMapper.isExist(user);
        System.out.println(exist);
    }
    
    @Test
    public void checkPermission() {
        
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        Integer exist = userMapper.checkPermission(user);
        System.out.println(exist);
    }
}
