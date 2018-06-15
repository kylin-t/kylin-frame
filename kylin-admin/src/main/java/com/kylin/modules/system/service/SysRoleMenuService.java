package com.kylin.modules.system.service;

import java.util.List;

/**
 * @Description: 角色与菜单对应关系
 * @author: kylin
 * @create: 2018-01-30 11:15
 **/
public interface SysRoleMenuService {
	
	void saveOrUpdate(Long roleId, Long[] menuIdArr);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
	
}
