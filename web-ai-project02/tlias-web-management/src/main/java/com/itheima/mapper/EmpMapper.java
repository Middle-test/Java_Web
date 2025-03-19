package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    //----------------原始分页查询实现-----------------
    //    /**
    //     * 查询总记录数
    //     */
    //   @Select("select count(*) from emp left join dept on dept_id = dept.id")
    //   public Long count();
    //
    //    /**
    //     * 分页查询
    //     */
    //    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
    //            "order by e.update_time desc limit #{start},#{pageSize} ")
    //    public List<Emp> list(Integer start, Integer pageSize);

//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//                "order by e.update_time desc  ")

    //    /**
//     * 这里使用xml映射文件定义SQL语句
//     * @param name
//     * @param gender
//     * @param begin
//     * @param end
//     * @return
//     */
//        public List<Emp> list(String name, Integer gender,
//                              LocalDate begin, LocalDate end);
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取生成的主键并返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 批量删除员工基本信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息
     */
    Emp getById(Integer id);

    /**
     * 根据ID修改员工基本信息
     *
     * @param emp
     */
    void updateById(Emp emp);
}
