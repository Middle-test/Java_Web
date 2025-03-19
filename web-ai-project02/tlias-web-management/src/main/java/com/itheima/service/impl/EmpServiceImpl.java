package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

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

    @Transactional(rollbackFor = RuntimeException.class) //事务控制,默认RuntimeException时回滚
    @Override
    public void save(Emp emp) {
        try {
            //1、保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2、保存员工工作信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //遍历集合，为每个员工工作信息设置员工id(empId)
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3、记录操作日志,不管成功还是失败，都要记录日志
            // （注意，由于受save事务的影响，回滚后无法记录日志，需要在insertLog设置新事务）
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //1、删除员工基本信息
        empMapper.deleteByIds(ids);

        //2、删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工信息
     *
     * @param emp
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1、根据ID修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2、根据ID修改员工工作经历信息（先删除再添加）
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
}
