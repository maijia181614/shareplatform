package com.shareplatform.demo.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/***
* @description: 日志切面
* @param 
* @return 
* @throws 
* @author mjq
* @date 2021/06/19 17:16
*/
@Component
@Aspect
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(LogAspect.class);

    //controller包 所有类方法切入点
    @Pointcut("execution(public * com.shareplatform.demo.controller..*.*(..))")
    public void controllerLog(){};

    /***
    * @description: controller前置通知，输出基本请求信息到日志
    * @param [joinPoint]
    * @return void
    * @throws 
    * @author mjq
    * @date 2021/06/19 17:35
    */
    @Before("controllerLog()")
    public void log(JoinPoint joinPoint){

        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=((ServletRequestAttributes)requestAttributes).getRequest();
        logger.info("+++++++++请求地址:"+request.getRequestURL().toString()+"+++++++++");
        logger.info("+++++++++方法:"+request.getMethod()+"+++++++++");
        logger.info("+++++++++源ip地址:"+request.getRemoteAddr()+"+++++++++");
        logger.info("+++++++++源端口:"+request.getRemotePort()+"+++++++++");
        logger.info("+++++++++类与方法名 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"+++++++++");
        logger.info("+++++++++请求参数:"+ Arrays.toString(joinPoint.getArgs())+"+++++++++");

    }
}
