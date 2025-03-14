package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 根据id删除部门
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    /**
     * 添加部门
     *
     * @param dept
     */
    @Override
    public void add(Dept dept) {
        //1、补全基础属性：createTime,updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        //2、调用mapper
        deptMapper.add(dept);
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        //1、补全基础属性：updateTime
        dept.setUpdateTime(LocalDateTime.now());
        //2、调用mapper
        deptMapper.update(dept);
    }

}
