package com.jkdx.example.homework05.annTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
//@Configuration
public class BeanTest {


    public String name;

    @Bean(initMethod = "init")
    public User insert() {

        User test_ = new User();

        test_.setUserNameCn(" user name cn is qinhui");

        return test_;
    }


    public String getName() {
        return name;
    }
}
