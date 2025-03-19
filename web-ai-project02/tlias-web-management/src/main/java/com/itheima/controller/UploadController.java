package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    /**
     * 阿里云OSS存储方案
     */
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 本地磁盘存储方案
     */
    /*@PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
        log.info("文件上传：{},{},{}", name, age, file.getOriginalFilename());

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //获取文件扩展名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        //新的文件名
        String newFileName = UUID.randomUUID().toString()+extension;

        //保存文件
        file.transferTo(new File("D:/"+newFileName));
        return Result.success();
    }*/
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}", file.getOriginalFilename());

        //将文件交给OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传到OSS的url：{}", url);

        return Result.success(url);
    }
}
