package com.itheima.springbootwebdemo.dao.impl;

import cn.hutool.core.io.IoUtil;
import com.itheima.springbootwebdemo.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@Component
@Repository //将当前类交给IOC容器管理
public class UserDaoImpl implements UserDao {
    @Override
    public List<String> findALl() {
        //加载并读取user.txt文件，获取用户数据
//        InputStream in = new FileInputStream("src/main/resources/user.txt");
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
