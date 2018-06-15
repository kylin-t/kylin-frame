package com.kylin.modules.scheduler.utils;

import com.google.gson.Gson;
import com.kylin.core.exception.RRException;
import com.kylin.core.utils.Constant;
import com.kylin.modules.scheduler.entity.ScheduleJob;
import org.quartz.*;

/**
 * @Description: 定时任务工具类
 * @author: kylin
 * @create: 2018-02-06 13:31
 **/
public class ScheduleUtils {
    private final static String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     * @param jobId
     * @return
     */
    private static TriggerKey getTriggerKey(Long jobId){
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobkey
     * @param jobId
     * @return
     */
    private static JobKey getJobKey(Long jobId){
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     * @param scheduler
     * @param jobId
     * @return
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId){
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RRException("getCronTriggery异常，请检查qrtz开头的表，是否有脏数据", e);
        }
    }

    /**
     * 创建定时任务
     * @param scheduler
     * @param scheduleJob
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob){
        try {
            //构建job
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJobTask.class)
                                            .withIdentity(getJobKey(scheduleJob.getJobId())).build();
            //构建cron
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                                                                         .withMisfireHandlingInstructionDoNothing();
            //根据cron,构建一个CronTrigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId()))
                                                .withSchedule(cronScheduleBuilder).build();
            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY,new Gson().toJson(scheduleJob));

            scheduler.scheduleJob(jobDetail,trigger);

            //暂停任务
            if(scheduleJob.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()){
                pauseJob(scheduler,scheduleJob.getJobId());
            }
        } catch (SchedulerException e) {
            throw new RRException("创建定时任务失败",e);
        }
    }

    /**
     * 更新定时任务
     * @param scheduler
     * @param scheduleJob
     */
    public static void updateScheduleJob(Scheduler scheduler,ScheduleJob scheduleJob){
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());
            //构建表达式调度
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = getCronTrigger(scheduler,scheduleJob.getJobId());
            //按新的cronExpression表达式重新构建trigger
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //参数
            cronTrigger.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, new Gson().toJson(scheduleJob));

            scheduler.rescheduleJob(triggerKey, cronTrigger);

            //暂停任务
            if(scheduleJob.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()){
                pauseJob(scheduler, scheduleJob.getJobId());
            }
        }catch (SchedulerException e){
            throw new RRException("更新定时任务失败",e);
        }
    }

    public static void run(Scheduler scheduler,ScheduleJob scheduleJob){
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(ScheduleJob.JOB_PARAM_KEY,new Gson().toJson(scheduleJob));

            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()),jobDataMap);
        } catch (SchedulerException e) {
            throw new RRException("立即执行任务失败",e);
        }
    }

    /**
     * 暂停任务
     * @param scheduler
     * @param jobId
     */
    public static void pauseJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RRException("暂停定时任务失败",e);
        }
    }

    /**
     * 恢复任务
     * @param scheduler
     * @param jobId
     */
    public static void resumeJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RRException("恢复定时任务失败",e);
        }
    }

    /**
     * 删除定时任务
     * @param scheduler
     * @param jobId
     */
    public static void deleteJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RRException("删除定时任务失败",e);
        }
    }
}





















