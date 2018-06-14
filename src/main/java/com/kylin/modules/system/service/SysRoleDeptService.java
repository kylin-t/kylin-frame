package com.kylin.modules.system.service;

import java.util.List;

/**
 * @Description: 角色与部门对应关系 服务类
 * @author: kylin
 * @create: 2018-01-30 15:31
 **/
public interface SysRoleDeptService {
    void saveOrUpdate(Long roleId, List<Long> deptIdList);
    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Long roleId);
}
