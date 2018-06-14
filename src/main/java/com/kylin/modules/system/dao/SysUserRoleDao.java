package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户与角色对应关系Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRole> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
