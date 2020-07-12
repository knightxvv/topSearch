package com.tx.log.aop.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class logAopService {
    public logAopService() {
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
    }
    @Pointcut("execution(* com.tx.user.controller.UserController.*(..))") 
    public void point() {
        
    }
    
//     前置通知
    @Before("point()")
    public void begin() {
        System.out.println("前置通知");
    }
    // 后置通知
    @After("execution(* com.tx.user.controller.UserController.login(..))")
    public void commit() {
        System.out.println("用户登录成功");
    }
//    // 运行通知
//    @AfterReturning
//    public void returning() {
//        System.out.println("运行通知");
//    }
//    // 异常通知
//    @AfterThrowing
//    public void afterThrowing() {
//        System.out.println("异常通知");
//    }
//    // 环绕通知
//    @Around(value = "")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("环绕通知开始");
//        proceedingJoinPoint.proceed();
//        System.out.println("环绕通知结束");
//    }
}

