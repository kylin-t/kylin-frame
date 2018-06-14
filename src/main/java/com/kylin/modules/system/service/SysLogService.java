package com.kylin.modules.system.service;

import com.kylin.modules.system.entity.SysLogEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description: 系统日志
 * @author: kylin
 * @create: 2018-01-30 11:15
 **/
public interface SysLogService {
	
	SysLogEntity queryObject(Long id);
	
	List<SysLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLogEntity sysLogEntity);

	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
