package com.itheima.springbootwebdemo.controller;

import cn.hutool.core.io.IoUtil;
import com.itheima.springbootwebdemo.pojo.User;
import com.itheima.springbootwebdemo.service.UserService;
import com.itheima.springbootwebdemo.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息Controller
 */

/**
 * 三层架构耦合：
 * 1、Controller层：接收请求，响应数据
 * 2、Service层：业务逻辑处理
 * 3、Dao层：数据访问操作
 */
@RestController
public class UserController {
//    //方式一：属性注入
//    @Autowired
//    private UserService userService;

//    //方式二：构造器注入
//    private  final UserService userService;
//    @Autowired //如果当前类中只存在一个构造函数，则该注解可省略
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    //方式三:Setter注入
    private  UserService userService;
    @Autowired
    //选择注入哪一个bean
    @Qualifier("userServiceImpl")
//    或者@Resource(name = "userServiceImpl")

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public List<User> list() throws Exception {
        /**
         * 这是写在一个方法里，复用性和维护性差
         */
        /*

        //1、加载并读取user.txt文件，获取用户数据
//        InputStream in = new FileInputStream("src/main/resources/user.txt");
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());

        //2、解析用户信息，封装成User对象 -> list集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.valueOf(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.valueOf(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).toList();

        //3、返回数据（json）
        return userList;
        */
        /**
         * 这是三层架构
         */
        //1、调用service获取数据
        //通过userService.findALl(),调用userDao.findALl()读取数据，然后进行处理，返回
        List<User> userList = userService.findALl();

        //2、返回数据（json）
        return userList;
    }
}
