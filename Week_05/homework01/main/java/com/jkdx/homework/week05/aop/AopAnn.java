package com.jkdx.homework.week05.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AopAnn {



    @Pointcut(value="execution(* com.jkdx.homework.week05.aop.*.*(..))")
    public void point(){

    }

    @Before(value="point()")
    public void before(){
        System.out.println("========>begin klass dong...");
    }

    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("========>after klass dong...");
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("========>around begin klass dong");
        joinPoint.proceed();
        System.out.println("========>around after klass dong");

    }



}
