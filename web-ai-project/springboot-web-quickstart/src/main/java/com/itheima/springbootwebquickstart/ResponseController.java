package com.itheima.springbootwebquickstart;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
    /**
     * 方式一：HttpServletResponse 设置响应数据
     */
    @RequestMapping("/response")//映射请求路径
    public void response(HttpServletResponse response) {
        //1、响应状态码
        response.setStatus(401);
        //2、响应头
        response.setHeader("name", "cyx");
        //3、响应体
        try {
            response.getWriter().write("<h1>hello response</h1>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式二：ResponseEntity 设置响应数据
     */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2(HttpServletResponse response) {
        return ResponseEntity
                .status(401)//设置响应状态码
                .header("name", "CYX")//设置响应头
                .body("<h1>hello response</h1>");//设置响应体
    }
}
