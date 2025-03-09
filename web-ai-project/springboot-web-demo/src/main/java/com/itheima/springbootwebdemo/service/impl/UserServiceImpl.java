package com.itheima.springbootwebdemo.service.impl;

import com.itheima.springbootwebdemo.dao.UserDao;
import com.itheima.springbootwebdemo.dao.impl.UserDaoImpl;
import com.itheima.springbootwebdemo.pojo.User;
import com.itheima.springbootwebdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Component
@Primary // 默认情况下，如果有多个bean，这个注解指定这个bean会被注入到IOC容器中
@Service //将当前类交给IOC容器管理
public class UserServiceImpl implements UserService {
    @Autowired //应用程序运行时会自动的查询该类型的bean对象，并赋值给该成员变量
    private UserDao userDao;
    @Override
    public List<User> findALl() {
        //1、调用dao获取数据
        List<String> lines = userDao.findALl();
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

        return userList;
    }
}
