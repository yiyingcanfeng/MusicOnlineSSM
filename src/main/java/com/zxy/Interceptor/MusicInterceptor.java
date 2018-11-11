package com.zxy.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MusicInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        boolean flag=true;
        String referer=request.getHeader("Referer");
        //获取发送请求的服务器的主机名
        String sitePart="http://"+request.getServerName();
        //判断referer是否为空，这个头的首地址是否以sitePart开始的
        if (referer!=null && referer.startsWith(sitePart)){
            flag=true;
        }else{
            response.getWriter().write("你没有权限访问此资源！");
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
