package com.mayikt.entity;

/**
 * @ClassName UserEntity
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class UserEntity {
    private String userName;
    private Integer age;

    public UserEntity(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
