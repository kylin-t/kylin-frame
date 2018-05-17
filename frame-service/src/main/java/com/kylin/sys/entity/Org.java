package com.kylin.sys.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:  组织机构
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:17
 */
public class Org implements Serializable {

    /**
     * 部门id
     */
    private Long orgId;
    /**
     * 上级部门Id,一级部门为0
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String orgName;
    /**
     * 上级部门名称
     */
    private String parentName;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 逻辑删除
     */
    private Integer delFlag;
    /**
     * 组织输属性
     */
    private Boolean open;
    private List<?> list;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
