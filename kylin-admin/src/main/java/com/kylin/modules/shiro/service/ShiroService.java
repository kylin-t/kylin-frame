package com.kylin.modules.shiro.service;

import com.kylin.modules.system.entity.SysUser;
import com.kylin.modules.system.entity.SysUserToken;

import java.util.Set;

/**
 * @Description: shiro相关接口
 * @author: kylin
 * @create: 2018-01-31 10:06
 **/
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(Long userId);
}
