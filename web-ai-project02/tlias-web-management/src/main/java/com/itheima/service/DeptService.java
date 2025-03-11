package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     *
     * @return
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门信息
     *
     * @param id
     */
    void deleteById(Integer id);
}
