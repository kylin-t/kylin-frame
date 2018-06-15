package com.kylin.core.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/06/04 19:40
 */
public class Query implements Serializable {

    //当前页码
    private Integer pageNum;
    //每页条数
    private Integer pageSize;
    /**
     * 名称
     */
    private String queryName;
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
    /**
     * 字典分类key
     */
    private String typeKey;
    /**
     * 部门id
     */
    private Long deptId;

    private Map<String, Object> params;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum != null) {
            this.pageNum = pageNum - 1;
        } else {
            this.pageNum = pageNum;
        }

    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        if ("0".equals(typeKey)) {
            this.typeKey = null;
        } else {
            this.typeKey = typeKey;
        }
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        if (0 == deptId) {
            this.deptId = null;
        } else {
            this.deptId = deptId;
        }
    }
}
