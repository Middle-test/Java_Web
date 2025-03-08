package com.itheima.springbootwebquickstart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//标识当前类是一个请求处理类
public class HelloController {
    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("name:" + name);
        return "Hello " + name + "!";
    }
}
