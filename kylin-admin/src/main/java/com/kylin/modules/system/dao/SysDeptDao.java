package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 部门管理 Mapper 接口
 * </p>
 *
 * @author kylin
 * @since 2017-11-21
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDept> {

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);
	/**
	 * 根据本部门ID获取获取本部门以及下属所有部门
	 * @param deptId
	 * @return
	 */
	List<SysDept> getChildDepts(@Param("deptId") Long deptId);

	List<Long> getChildDeptIds(Long deptId);
}