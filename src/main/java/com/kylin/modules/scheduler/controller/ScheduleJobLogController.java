package com.kylin.modules.scheduler.controller;

import com.kylin.common.Result.Result;
import com.kylin.common.utils.*;
import com.kylin.modules.scheduler.entity.ScheduleJobLog;
import com.kylin.modules.scheduler.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 定时任务日志
 * @author: kylin
 * @create: 2018-02-07 9:37
 **/
@RequestMapping("/schedule/log")
@RestController
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    public Result list(Query query){
        //查询列表数据
        Map<String,Object> conditionMap = BeanMap.objectToMap(query);
        List<ScheduleJobLog> jobList = scheduleJobLogService.queryList(conditionMap);
        int total = scheduleJobLogService.queryTotal(conditionMap);

        PageUtils pageUtil = new PageUtils(jobList, total, query.getPageSize(), query.getPageNum());

        return Result.success(pageUtil);
    }

    /**
     * 定时任务日志信息
     */
    @RequestMapping("/info/{logId}")
    public R info(@PathVariable("logId") Long logId){
        ScheduleJobLog log = scheduleJobLogService.queryObject(logId);

        return R.ok().put("log", log);
    }
}
