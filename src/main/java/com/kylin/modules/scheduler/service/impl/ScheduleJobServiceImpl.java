package com.kylin.modules.scheduler.service.impl;

import com.kylin.common.utils.Constant;
import com.kylin.modules.scheduler.dao.ScheduleJobDao;
import com.kylin.modules.scheduler.entity.ScheduleJob;
import com.kylin.modules.scheduler.service.ScheduleJobService;
import com.kylin.modules.scheduler.utils.ScheduleUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 定时任务业务实现类
 * @author: kylin
 * @create: 2018-02-06 16:40
 **/
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private ScheduleJobDao scheduleJobDao;
    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时初始化定时器
     */
    @PostConstruct
    public void init(){
        List<ScheduleJob> scheduleJobList = scheduleJobDao.queryList(new HashMap<>());
        for (ScheduleJob scheduleJob:scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler,scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public ScheduleJob queryObject(Long jobId) {
        return scheduleJobDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJob> queryList(Map<String, Object> map) {
        return scheduleJobDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());

        scheduleJobDao.save(scheduleJob);

        ScheduleUtils.createScheduleJob(scheduler,scheduleJob);
    }

    @Override
    @Transactional
    public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler,scheduleJob);
        scheduleJobDao.update(scheduleJob);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds){
            ScheduleUtils.deleteJob(scheduler,jobId);
        }
        scheduleJobDao.deleteBatch(jobIds);
    }

    @Override
    public void updateBactch(Long[] jobIds, int status) {
        Map<String,Object> map = new HashMap<>();
        map.put("list",jobIds);
        map.put("status",status);
        scheduleJobDao.updateBatch(map);
    }

    @Override
    @Transactional
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds){
            ScheduleUtils.run(scheduler,queryObject(jobId));
        }
    }

    @Override
    @Transactional
    public void pause(Long[] jobIds) {
        for (Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler,jobId);
        }
        updateBactch(jobIds,Constant.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional
    public void resume(Long[] jobIds) {
        for (Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler,jobId);
        }
        updateBactch(jobIds,Constant.ScheduleStatus.NORMAL.getValue());
    }
}
