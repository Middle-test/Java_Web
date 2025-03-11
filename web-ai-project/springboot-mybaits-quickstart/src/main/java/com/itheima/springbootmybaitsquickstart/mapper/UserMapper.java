package com.itheima.springbootmybaitsquickstart.mapper;

import com.itheima.springbootmybaitsquickstart.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序在运行时，会自动的为该接口创建一个实现类对象（代理对象），并且会自动将该实现类对象存入IOC容器 -bean
public interface UserMapper {
    /**
     * 查询所有用户
     * 这里使用xml设置映射关系，使用注解也可以设置映射关系
     */
    public List<User> findAll();

    /**
     * 根据id删除用户信息，使用了占位符,在编译时会替换成jdbc的占位符?
     * 注意，如果使用${}这个占位符，则可以拼接表达式，不安全
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    public Integer deleteById(Integer id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Insert("insert into user(username, password, name, age) values (#{username},#{password},#{name},#{age})")
    public Integer insert(User user);

    /**
     * 更新用户信息
     */
    @Update("update user set username = #{username},password = #{password},name = #{name},age = #{age} where id = 1")
    public Integer update(User user);

    /**
     * 根据用户名和密码查询用户信息
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    //由于字节码文件中形参名称不会保留，并且存在多个参数，所以需要使用@Param注解指定参数名
    //基于官方骨架创建出来的springboot项目，Param注解可以省略
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
