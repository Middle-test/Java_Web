package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);// 日志
    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门数据
     */
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() {
        log.info("查询全部部门数据");
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
        log.info("根据ID删除部门： " + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门： " + dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id 查询部门
     */
    @GetMapping("/depts/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("根据id查询部门： " + id);
        Dept info = deptService.findById(id);
        return Result.success(info);
    }


    /**
     * 根据id 更新部门
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("根据id更新部门： " + dept);
        deptService.update(dept);
        return Result.success();
    }

}

