package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 角色与菜单对应关系Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
