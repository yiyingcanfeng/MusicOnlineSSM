package com.zxy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {
    
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    
    /**
     * 公共切入点
     */
    @Pointcut("execution(* com.zxy.controller.UserController.*(..))")
    public void log() { }
    
    /**
     * 使用AOP对Controller类进行日志记录
     * @param joinPoint 切入点
     * @param result 无
     */
    @AfterReturning(value = "execution(* com.zxy.controller.*.*(..))",returning = "result")
    public void customerControllerLog(JoinPoint joinPoint, Object result) {
        logger.info("\n方法名:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+
                    "\n参数:"+Arrays.toString(joinPoint.getArgs())+
                    "\n返回值:"+result);
    }
}
