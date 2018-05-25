package com.kylin.sys.entity;

import com.kylin.validator.group.AddGroup;
import com.kylin.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description:  系统角色
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:17
 */
public class Role {

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String roleName;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 角色状态  1：正常  0  禁用
     */
    private Integer status = 0;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 备注
     */
    private String remark;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
