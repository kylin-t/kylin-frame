package com.kylin.sys.dao;

import com.kylin.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:  RoleDao组件
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:52
 */
@Mapper
public interface RoleDao extends BaseDao<Role> {

    List<Role> queryByUserId(@Param("userId") Long userId, @Param("status") Integer status);
}
