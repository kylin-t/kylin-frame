package com.kylin.modules.system.service;


import com.kylin.modules.system.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @Description: 系统角色
 * @author: kylin
 * @create: 2018-01-30 11:15
 **/
public interface SysRoleService {
	
	SysRole queryObject(Long roleId);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRole role);
	
	void update(SysRole role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
