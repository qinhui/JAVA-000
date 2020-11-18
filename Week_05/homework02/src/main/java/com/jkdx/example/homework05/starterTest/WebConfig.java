package com.jkdx.example.homework05.starterTest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
public class WebConfig {

    public String url;
    public String name;
    public String pwd;


    @Bean
    public String value(){
        return "test-Value";
    }




}
