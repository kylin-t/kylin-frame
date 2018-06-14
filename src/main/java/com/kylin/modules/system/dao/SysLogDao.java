package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 系统日志Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
