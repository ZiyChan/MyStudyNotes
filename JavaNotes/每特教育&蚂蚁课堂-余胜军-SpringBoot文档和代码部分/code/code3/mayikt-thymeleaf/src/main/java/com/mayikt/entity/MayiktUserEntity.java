package com.mayikt.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName MayiktUserEntity
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@Component
@ConfigurationProperties(prefix = "mayikt")
public class MayiktUserEntity {
    private String name;
    private String age;
    private String addres;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddres() {
        return addres;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Override
    public String toString() {
        return "MayiktUserEntity{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", addres='" + addres + '\'' +
                '}';
    }
}
