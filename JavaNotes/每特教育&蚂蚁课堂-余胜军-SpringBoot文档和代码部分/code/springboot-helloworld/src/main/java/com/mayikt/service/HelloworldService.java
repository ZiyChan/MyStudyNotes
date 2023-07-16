package com.mayikt.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloworldService
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@RestController
//@EnableAutoConfiguration
//@ComponentScan("com.mayikt.service")
public class HelloworldService {

    /**
     * @RestController 与@Controller之间区别
     * 如果在类上加上@RestController，该类中所有SpringMVCUrl接口映射都是返回json格式
     * 在每个方法上加上@ResponseBody注解 返回json格式
     * @RestController 是我们SpringMVC提供 而不是Springboot提供
     * <p>
     * Rest 微服务接口开发中 Rest风格 数据传输格式json格式 协议http协议
     * <p>
     * Controller 控制层注解 SpringMVCUrl接口映射 默认的情况下返回页面跳转 如果需要返回json格式的情况下需要@ResponseBody注解
     */

    @RequestMapping("/getUser")
    public String getUser() {
        // 转发到mayikt
        return "mayikt";
    }

//    public static void main(String[] args) {
//        /**
//         * 启动类入口class 默认整合Tomcat容器端口8080
//         */
//        SpringApplication.run(HelloworldService.class, args);
//    }


}
