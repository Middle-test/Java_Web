package com.itheima.service;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

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

    /**
     * 批量删除员工
     *
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工信息
     *
     * @param emp
     */
    void update(Emp emp);
}
