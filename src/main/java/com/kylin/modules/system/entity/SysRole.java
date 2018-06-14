package com.kylin.modules.system.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @Description: 系统角色
 * @author: kylin
 * @create: 2018-01-30 11:14
 **/
public class SysRole {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message="角色名称不能为空")
    private String roleName;
    /**
     * 角色编码
     */
    @NotBlank(message="角色编码不能为空")
    private String roleNum;
    /**
     * 角色级别
     */
    private Integer levelNum;
    /**
     * 状态  1正常 0禁用
     */
    private Integer status = 1;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 角色对应菜单列表
     */
    private List<Long> menuIdList;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 设置：
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：
     * @return Long
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置：角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取：角色名称
     * @return String
     */
    public String getRoleName() {
        return roleName;
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
    }

    /**
     * 设置：备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     * @return String
     */

    public String getRemark() {
        return remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
