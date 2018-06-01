package com.kylin.sys.dao;

import com.kylin.sys.entity.UserRole;

import java.util.List;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:52
 */
public interface UserRoleDao extends BaseDao<UserRole> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);
}
