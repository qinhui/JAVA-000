package com.jkdx.homework.week05.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {

    public static  void main(String[] args){

        ApplicationContext context_ = new FileSystemXmlApplicationContext("E:\\git_qh\\week04\\src\\main\\java\\applicationContext.xml");


        Module moduleJZ =(Module) context_.getBean("moduleA");
       System.out.println(" test-------main-----------JZCODE = "+moduleJZ.mCode);
        Module moduleDOC = (Module) context_.getBean("moduleB");
        System.out.println(" test-------main-----------DOCCODE = "+moduleDOC.mCode);

        IProject project_ = context_.getBean(IProject.class);
//        System.out.println(project_);
        System.out.println("IProject接口的对象AOP代理后的实际类型："+project_.getClass());

        project_.editProject();





    }


}
