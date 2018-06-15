package com.kylin.modules.scheduler.controller;

import com.kylin.core.Result.Result;
import com.kylin.core.annotation.SysLog;
import com.kylin.core.utils.*;
import com.kylin.core.validator.ValidatorUtils;
import com.kylin.modules.scheduler.entity.ScheduleJob;
import com.kylin.modules.scheduler.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 定时任务
 * @author: kylin
 * @create: 2018-02-07 9:32
 **/
@RestController
@RequestMapping("/schedule/job")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public Result list(Query query){
        //查询列表数据
        Map<String,Object> conditionMap = BeanMap.objectToMap(query);
        List<ScheduleJob> jobList = scheduleJobService.queryList(conditionMap);
        int total = scheduleJobService.queryTotal(conditionMap);

        PageUtils pageUtil = new PageUtils(jobList, total, query.getPageSize(), query.getPageNum());

        return Result.success(pageUtil);
    }

    /**
     * 定时任务信息
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:schedule:info")
    public Result info(Long jobId){
        ScheduleJob schedule = scheduleJobService.queryObject(jobId);

        return Result.success(schedule);
    }

    /**
     * 保存定时任务
     */
    @SysLog("保存定时任务")
    @RequestMapping("/save")
    @RequiresPermissions("sys:schedule:save")
    public Result save(ScheduleJob scheduleJob){
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.save(scheduleJob);

        return Result.success();
    }

    /**
     * 修改定时任务
     */
    @SysLog("修改定时任务")
    @RequestMapping("/update")
    @RequiresPermissions("sys:schedule:update")
    public Result update(ScheduleJob scheduleJob){
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.update(scheduleJob);

        return Result.success();
    }

    /**
     * 删除定时任务
     */
    @SysLog("删除定时任务")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:schedule:delete")
    public Result delete(@RequestBody Long[] jobIds){
        scheduleJobService.deleteBatch(jobIds);

        return Result.success();
    }

    /**
     * 立即执行任务
     */
    @SysLog("立即执行任务")
    @RequestMapping("/run")
    @RequiresPermissions("sys:schedule:run")
    public Result run(String jobId){
        Long[] jobIds = ArrayUtil.strToLongArr(jobId);
        scheduleJobService.run(jobIds);

        return Result.success();
    }

    /**
     * 暂停定时任务
     */
    @SysLog("暂停定时任务")
    @RequestMapping("/pause")
    @RequiresPermissions("sys:schedule:pause")
    public Result pause(String jobId){
        Long[] jobIds = ArrayUtil.strToLongArr(jobId);
        scheduleJobService.pause(jobIds);

        return Result.success();
    }

    /**
     * 恢复定时任务
     */
    @SysLog("恢复定时任务")
    @RequestMapping("/resume")
    @RequiresPermissions("sys:schedule:resume")
    public Result resume(String jobId){
        Long[] jobIds = ArrayUtil.strToLongArr(jobId);
        scheduleJobService.resume(jobIds);

        return Result.success();
    }
}
