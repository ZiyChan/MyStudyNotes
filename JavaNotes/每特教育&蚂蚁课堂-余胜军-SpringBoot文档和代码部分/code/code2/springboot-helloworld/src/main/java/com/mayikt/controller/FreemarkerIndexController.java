package com.mayikt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName FreemarkerIndex
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@Controller
public class FreemarkerIndexController {
    @RequestMapping("/freemarkerIndex")
    public String freemarkerIndex(Map<String, Object> result, HttpServletRequest request) {
        // 转发到页面展示数据 name valuemayikt
        result.put("name", "mayikt");
        // 0是为男 1是为女
        result.put("sex", "0");
        result.put("age", 22);
        ArrayList<String> userlist = new ArrayList<>();
        userlist.add("mayikt");
        userlist.add("xiaowei");
        result.put("userList", userlist);
//        request.setAttribute("name",mayikt);
        return "freemarkerIndex";
    }
}
