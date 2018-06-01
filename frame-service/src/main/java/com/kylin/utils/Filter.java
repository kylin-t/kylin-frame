package com.kylin.utils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/06/01 14:51
 */
public class Filter implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int pageNum;
    //每页条数
    private int pageSize;
    /**
     * 名称
     */
    private String name;
    /**
     * id
     */
    private Long id;
    /**
     * 日期
     */
    private Date date;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序方式
     */
    private String order;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
