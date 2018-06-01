package com.kylin.sys.service;

import java.util.List;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 16:01
 */
public interface UserRoleService{

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    void deleteBatch(Long[] userId);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

}
