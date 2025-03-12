package com.itheima.pojo;

import lombok.Data;

import java.util.List;

/**
 * 分页结果展示类
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}
