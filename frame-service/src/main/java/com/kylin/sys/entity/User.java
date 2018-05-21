package com.kylin.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kylin.validator.group.AddGroup;
import com.kylin.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description:  系统用户
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/28 23:39
 */
public class User implements Serializable {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名用户名
     */
    @NotBlank(message = "用户名不能为空",groups ={AddGroup.class,UpdateGroup.class})
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空",groups = AddGroup.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确",groups = {AddGroup.class,UpdateGroup.class})
    private String email;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 账号状态  0  禁用   1   正常
     */
    private Integer status;
    /**
     * 角色ID列表
     */
    private List<Long> roleList;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 所在部门id
     */
    @NotNull(message = "所在部门不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private Long orgId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Long> roleList) {
        this.roleList = roleList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
