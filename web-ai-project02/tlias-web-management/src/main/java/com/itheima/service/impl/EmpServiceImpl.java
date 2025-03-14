package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    //----------------原始分页查询实现-----------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1、调用Mapper接口，查询总记录数
//        Long count = empMapper.count();
//
//        //2、调用Mapper接口，查询结果列表
//        List<Emp> rows = empMapper.list((page-1)*pageSize, pageSize);
//
//        //3、封装结果为PageResult对象
//        return new PageResult<Emp>(count,rows);
//    }

    //----------------基于PageHelper分页查询-----------------

    /**
     * 注意：
     * 1、使用PageHelper插件，定义的SQL语句结尾不能加分号
     * 2、PageHelper插件仅仅能对紧跟在其后的第一个查询语句进行分页处理
     */
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
//                                LocalDate begin, LocalDate end) {
//        //1、设置分页参数
//        PageHelper.startPage(page,pageSize);
//
//        //2、执行查询
//        List<Emp> empList = empMapper.list(name,gender,begin,end);
//
//        //3、解析查询结果并封装
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1、设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2、执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3、解析查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Emp emp) {
        //1、保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //2、保存员工工作信息
        List<EmpExpr> exprList = emp.getEmpExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历集合，为每个员工工作信息设置员工id(empId)
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
}
