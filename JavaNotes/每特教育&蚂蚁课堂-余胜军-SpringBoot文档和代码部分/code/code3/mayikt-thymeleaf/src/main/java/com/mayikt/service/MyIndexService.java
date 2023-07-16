package com.mayikt.service;

import com.mayikt.entity.MayiktUserEntity;
import com.mayikt.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyIndexService
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@RestController
public class MyIndexService {

    @Value("${mayikt.name}")
    private String name;

    @Value("${mayikt.age}")
    private String age;

    @Value("${mayikt.addres}")
    private String addres;

    @Autowired
    private MayiktUserEntity mayiktUserEntity;

    /**
     * 使用   @Value
     *
     * @return
     */
    @RequestMapping("/getNameAndAge")
    public String getNameAndAge() {
        return name + "," + age;
    }

    /**
     * ConfigurationProperties
     *
     * @return
     */
    @RequestMapping("/getNameAndAgeAddres")
    public String getNameAndAgeAddres() {
        return mayiktUserEntity.toString();
    }
}
