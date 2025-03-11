package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门数据
     */
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门 - 方式一：HttpServletRequest 获取请求参数
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据ID删除部门： "+id);
//        return Result.success();
//    }

    /**
     * 删除部门 - 方式二：@RequestParam注解获取请求参数
     * 注意：一旦声明了@RequestParam注解，那么请求参数中就必须有该参数，否则会报错
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer deptId){
//        System.out.println("根据ID删除部门： "+deptId);
//        return Result.success();
//    }

    /**
     * 删除部门 - 方式三：如果请求参数的名称和形参名称一致，那么@RequestParam注解可以省略
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        System.out.println("根据ID删除部门： " + id);
        deptService.deleteById(id);
        return Result.success();
    }

}

