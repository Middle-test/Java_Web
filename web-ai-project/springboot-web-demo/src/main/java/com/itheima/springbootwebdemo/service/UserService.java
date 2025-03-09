package com.itheima.springbootwebdemo.service;

import com.itheima.springbootwebdemo.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户信息
     */
    public List<User> findALl();
}
