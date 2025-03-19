package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历信息
     * 使用xml映射
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id删除员工工作经历信息
     *
     * @param empIds
     */
    void deleteByEmpIds(List<Integer> empIds);
}
