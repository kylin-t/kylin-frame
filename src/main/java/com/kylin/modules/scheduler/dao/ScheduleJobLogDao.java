package com.kylin.modules.scheduler.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.scheduler.entity.ScheduleJobLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 定时执行日志dao
 * @author: kylin
 * @create: 2018-02-06 16:29
 **/
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLog> {
}
