package com.kylin.modules.scheduler.service.impl;

import com.kylin.modules.scheduler.dao.ScheduleJobLogDao;
import com.kylin.modules.scheduler.entity.ScheduleJobLog;
import com.kylin.modules.scheduler.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 定时任务日志业务实现类
 * @author: kylin
 * @create: 2018-02-06 18:31
 **/
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

    @Autowired
    private ScheduleJobLogDao scheduleJobLogDao;

    @Override
    public ScheduleJobLog queryObject(Long jobId) {
        return scheduleJobLogDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJobLog> queryList(Map<String, Object> map) {
        return scheduleJobLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobLogDao.queryTotal(map);
    }

    @Override
    public void save(ScheduleJobLog log) {
        scheduleJobLogDao.save(log);
    }
}
