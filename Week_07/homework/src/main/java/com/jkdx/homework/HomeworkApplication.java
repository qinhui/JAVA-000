package com.jkdx.homework;

import com.jkdx.homework.db.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {


        SpringApplication.run(HomeworkApplication.class, args);



    }

}
