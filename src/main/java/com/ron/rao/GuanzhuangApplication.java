package com.ron.rao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//跳过springboot自带的权限
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GuanzhuangApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuanzhuangApplication.class, args);
    }

}
