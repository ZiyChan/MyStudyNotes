package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName App
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
