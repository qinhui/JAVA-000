package com.jkdx.homework.week05.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopProject {

    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        joinPoint.proceed();
    }


    public void after(){

        System.out.println(" aop doc -- after---");


    }


    public void before(){

        System.out.println(" aop doc -- before---");


    }

}
