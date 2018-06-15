package com.kylin.modules.scheduler.utils;

import com.google.gson.Gson;
import com.kylin.core.utils.SpringContextUtils;
import com.kylin.modules.scheduler.entity.ScheduleJob;
import com.kylin.modules.scheduler.entity.ScheduleJobLog;
import com.kylin.modules.scheduler.service.ScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 定时任务
 * @author: kylin
 * @create: 2018-02-06 11:13
 **/
public class ScheduleJobTask extends QuartzJobBean{

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String jsonJob = jobDataMap.getString(ScheduleJob.JOB_PARAM_KEY);

        ScheduleJob scheduleJob = new Gson().fromJson(jsonJob,ScheduleJob.class);

        //获取scheduleJobLogService
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLog scheduleJobLog = new ScheduleJobLog();
        scheduleJobLog.setJobId(scheduleJob.getJobId());
        scheduleJobLog.setBeanName(scheduleJob.getBeanName());
        scheduleJobLog.setMethodName(scheduleJob.getMethodName());
        scheduleJobLog.setParams(scheduleJob.getParams());
        scheduleJobLog.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.info("任务准备执行，任务id：" + scheduleJob.getJobId());
            ScheduleRunnable scheduleRunnable = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(),scheduleJob.getParams());
            Future<?> future = service.submit(scheduleRunnable);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLog.setTimes((int) times);
            scheduleJobLog.setStatus(0);

            logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
           scheduleJobLog.setTimes((int) times);
           scheduleJobLog.setStatus(1);
           scheduleJobLog.setError(StringUtils.substring(e.toString(),0,2000));
            e.printStackTrace();
        }finally {
            scheduleJobLogService.save(scheduleJobLog);
        }

    }
}