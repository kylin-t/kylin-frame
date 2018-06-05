package com.kylin.sys.service;

import com.kylin.sys.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description:  系统角色service
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:58
 */
public interface RoleService {

    List<Role> queryList(Map<String,Object> map);

    void save(Role role);

    Role queryObject(Long roleId);

    void update(Role role);

    void deleteBatch(String roleIds);

    int queryTotal(Map<String,Object> map);

    /**
     * 根据用户ID查询拥有的所有角色
     * @param userId
     * @return
     */
    List<Role> queryByUserId(Long userId,Integer status);

}
