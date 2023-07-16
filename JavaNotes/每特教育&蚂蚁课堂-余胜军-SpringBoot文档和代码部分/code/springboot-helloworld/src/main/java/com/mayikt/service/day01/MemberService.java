package com.mayikt.service.day01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberService
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@RestController
public class MemberService {

    @RequestMapping("/getMember")
    public String getMember() {
        return "每特教育";
    }
}
