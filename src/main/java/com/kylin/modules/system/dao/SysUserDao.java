package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 系统用户Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysUserDao extends BaseDao<SysUser> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser queryByUserName(String username);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);
	/**
	 * 根据ID查询部门
	 */
	Long getDeptId(Long id);
}
