package com.zxy.Interceptor;

import com.zxy.model.User;
import com.zxy.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor{
    
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        boolean flag;
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            response.getWriter().write("你尚未登录或登录已失效！");
            return false;
        }
        //验证用户权限
        int i = userService.checkPermission(user);
        String referer=request.getHeader("Referer");
        String sitePart="http://"+request.getServerName();//获取发送请求的服务器的主机名
        //判断referer是否为空，这个头的首地址是否以sitePart开始的
        if (referer!=null && referer.startsWith(sitePart)){
            if (i==1){//1：管理员
                flag=true;
            } else{
                response.getWriter().write("你尚未登录或登录已失效！");
                flag=false;
            }
        }else{
            response.getWriter().write("你尚未登录或登录已失效！");
            flag=false;
        }
        return flag;
    }
    
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    
    }
    
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    
    }
}
