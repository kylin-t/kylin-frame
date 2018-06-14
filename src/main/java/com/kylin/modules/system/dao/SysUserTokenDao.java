package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.SysUserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 系统用户Token Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserToken> {
    
    SysUserToken queryByUserId(Long userId);

    SysUserToken queryByToken(String token);
	
}
