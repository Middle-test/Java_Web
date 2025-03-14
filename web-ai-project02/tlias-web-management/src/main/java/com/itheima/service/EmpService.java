package com.itheima.service;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

public interface EmpService {
    /**
     * 分页查询
     */
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,
//                          LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     */
    void save(Emp emp);

}
