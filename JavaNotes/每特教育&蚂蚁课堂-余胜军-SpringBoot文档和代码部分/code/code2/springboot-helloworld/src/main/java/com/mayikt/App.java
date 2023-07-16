package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName App
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@SpringBootApplication
public class App {
    /**
     * @param args
     * @SpringBootApplication 组合
     * @EnableAutoConfiguration
     * @ComponentScan
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
    /**
     *  @ComponentScan 扫包范围 com.mayikt.service
     *  当前启动类同级包或者子包下面
     */
}
