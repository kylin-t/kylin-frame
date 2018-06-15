package com.kylin.modules.system.service;

import com.kylin.modules.system.entity.SysDept;

import java.util.List;
import java.util.Map;

/**
 * @Description: 部门管理
 * @author: kylin
 * @create: 2018-01-30 11:15
 **/
public interface SysDeptService {

	SysDept queryObject(Long deptId);

	List<SysDept> queryList(Map<String, Object> map);
	
	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	public List<Long> queryDetpIdList(Long parentId);
	
	/**
	 * 获取子部门ID(包含本部门ID)，用于数据过滤
	 */
	String getSubDeptIdList(Long deptId);

	void save(SysDept sysDept);

	void update(SysDept sysDept);

	void delete(Long deptId);
	/**
	 *
	 * 根据本部门ID获取获取本部门以及下属所有部门
	 * @param deptId
	 * @return
	 */
	List<SysDept> getChildDepts(Long deptId);
	/**
	 *
	 * 根据本部门ID获取获取本部门以及下属所有部门IDS
	 * @param deptId
	 * @return
	 */
	String getChildDeptIds(Long deptId);
}
