package com.kylin.modules.system.controller;

import com.kylin.core.Result.Result;
import com.kylin.core.utils.*;
import com.kylin.modules.system.entity.SysLogEntity;
import com.kylin.modules.system.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 系统日志
 * @author: kylin
 * @create: 2018-01-31 10:28
 **/
@Api(tags = {"日志相关接口"})
@RequestMapping("/sys/log")
@RestController
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ApiOperation(value = "获取日志列表")
    @PostMapping("/list")
    @RequiresPermissions("sys:log:list")
    public Result list(Query query){
        //查询列表数据
        Map<String,Object> conditionMap = BeanMap.objectToMap(query);
        List<SysLogEntity> sysLogEntityList = sysLogService.queryList(conditionMap);
        int total = sysLogService.queryTotal(conditionMap);

        PageUtils pageUtil = new PageUtils(sysLogEntityList, total, query.getPageSize(), query.getPageNum());
        return Result.success(pageUtil);
    }
}
