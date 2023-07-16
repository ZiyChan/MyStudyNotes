package com.mayikt.service;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MayiktExceptionHandler
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@ControllerAdvice
public class MayiktExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<Object, Object> exceptionHandler() {
        HashMap<Object, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", "系统错误");
        return result;
    }
}
