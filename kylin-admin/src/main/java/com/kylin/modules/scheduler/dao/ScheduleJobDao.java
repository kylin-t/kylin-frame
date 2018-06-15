package com.kylin.modules.scheduler.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.scheduler.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @author: kylin
 * @create: 2018-02-06 11:31
 **/
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJob> {
}
