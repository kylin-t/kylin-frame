package com.kylin.modules.system.service;

import java.util.List;

/**
 * @Description: 用户与角色对应关系
 * @author: kylin
 * @create: 2018-01-30 11:15
 **/
public interface SysUserRoleService {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void delete(Long userId);
}
