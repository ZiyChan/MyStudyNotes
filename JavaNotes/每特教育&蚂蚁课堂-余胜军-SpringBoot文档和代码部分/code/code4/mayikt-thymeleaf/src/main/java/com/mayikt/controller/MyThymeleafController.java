package com.mayikt.controller;

import com.mayikt.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName MyThymeleafController
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@Controller
public class MyThymeleafController {
    private String addres;


    @RequestMapping("/myThymeleaf")
    public String myThymeleaf(HttpServletRequest request, Map<String, Object> result) {
        result.put("user", new UserEntity("mayikt", 22));
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt1", 22));
        userEntities.add(new UserEntity("mayikt2", 22));
        userEntities.add(new UserEntity("mayikt3", 22));
        result.put("userList", userEntities);
        return "myThymeleaf";
    }
}
