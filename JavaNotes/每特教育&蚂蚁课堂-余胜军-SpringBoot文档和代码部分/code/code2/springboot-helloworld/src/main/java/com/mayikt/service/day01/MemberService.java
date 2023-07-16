package com.mayikt.service.day01;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberService
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@RestController
public class MemberService {
    @Value("${mayikt.name}")
    private String name;
    @Value("${mayikt.age}")
    private String age;

    @RequestMapping("/getMember")
    public String getMember() {
        return "每特教育";
    }

    @RequestMapping("/getProperties")
    public String getProperties() {
        return name + "--" + age;
    }


}
