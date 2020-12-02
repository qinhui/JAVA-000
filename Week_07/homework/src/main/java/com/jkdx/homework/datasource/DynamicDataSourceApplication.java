package com.jkdx.homework.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


/**
 * @author qinhui
 * @version 1.0 2020/12/3
 */


@SpringBootApplication
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
@MapperScan("com.jkdx.homework.datasource")
public class DynamicDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);
    }

}
