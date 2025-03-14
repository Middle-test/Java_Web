package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门信息
     */
    //由于参数名不一致，无法自动映射

    //方式一：手动结果映射
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })

    //方式二：起别名
//    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")

    //方式三：开启驼峰命名（推荐）
    @Select("select id, name, create_time , update_time  from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 根据id删除部门
     *
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加部门
     *
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    /**
     * 根据id查询部门
     *
     * @param id
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept findById(Integer id);

    /**
     * 根据id修改部门
     *
     * @param dept
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
