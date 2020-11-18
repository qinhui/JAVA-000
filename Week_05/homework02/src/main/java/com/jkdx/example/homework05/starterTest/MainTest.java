package com.jkdx.example.homework05.starterTest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@EnableAutoConfiguration
public class MainTest {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(WebAutoConfig.class);
        TestBean student = (TestBean) applicationContext.getBean("testBean");

        System.out.println("--------------> " + student.getCode());
//


    }

}
