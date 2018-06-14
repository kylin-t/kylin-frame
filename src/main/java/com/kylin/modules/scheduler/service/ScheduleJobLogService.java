package com.kylin.modules.scheduler.service;

import com.kylin.modules.scheduler.entity.ScheduleJobLog;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: kylin
 * @create: 2018-02-06 16:31
 **/
public interface ScheduleJobLogService {
    /**
     * 根据ID，查询定时任务日志
     */
    ScheduleJobLog queryObject(Long jobId);

    /**
     * 查询定时任务日志列表
     */
    List<ScheduleJobLog> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务日志
     */
    void save(ScheduleJobLog log);
}
