package com.jkdx.example.homework05.starterTest;


import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(WebConfig.class)
public class WebAutoConfig {

    @Bean
    public static TestBean testBean(){
        WebConfig webConfig = new WebConfig();
        TestBean tb = new TestBean();
        tb.setName("秦会");
        tb.setCode( webConfig.value());

        return tb;
    }


}
