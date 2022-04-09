package com.yanszero.ytdemo.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yanszero") //要使用SWAGGER功能 但PACKAGE在不同位置 需要用掃描的方式
public class ServiceOilApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOilApplication.class, args);
    }
}

