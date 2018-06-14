package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 系统角色Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysRoleDao extends BaseDao<SysRole> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
