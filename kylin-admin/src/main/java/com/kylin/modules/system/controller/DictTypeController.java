package com.kylin.modules.system.controller;

import com.kylin.core.Result.Result;
import com.kylin.core.annotation.SysLog;
import com.kylin.common.base.controller.BaseController;
import com.kylin.core.utils.DateUtils;
import com.kylin.modules.system.entity.DictType;
import com.kylin.modules.system.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 数据字典分类接口
 * @author: kylin
 * @create: 2018-02-01 10:35
 **/
@Api(tags = {"数据字典分类接口"})
@RestController
@RequestMapping("/dict/type")
public class DictTypeController extends BaseController {

    @Autowired
    private DictTypeService dictTypeService;

    @ApiOperation(value = "获取列表")
    @PostMapping("/list")
    @RequiresPermissions("sys:value:list")
    public Result list(@RequestParam Map<String,Object> map){

        List<DictType> list = dictTypeService.queryList(map);

        return Result.success(list);
    }

    @PostMapping("/info")
    public Result info(Long id){
        DictType dictType = dictTypeService.queryObject(id);
        return Result.success(dictType);
    }

    @SysLog("新增字典")
    @PostMapping("/save")
    public Result save(DictType dictType){
        dictType.setUserId(getUserId());
        dictType.setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        dictTypeService.save(dictType);
        return Result.success();
    }
    /**
     * 修改字典
     */
    @SysLog("修改字典")
    @PostMapping("/update")
    public Result update(DictType dictType){
        dictType.setUserId(getUserId());
        dictType.setUpdateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));

        dictTypeService.update(dictType);
        return Result.success();
    }

    /**
     * 删除字典
     */
    @SysLog("删除字典")
    @GetMapping("/delete")
    public Result delete(Long id){
        dictTypeService.delete(id);
        return Result.success();
    }
}
