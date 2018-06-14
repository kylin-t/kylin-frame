package com.kylin.modules.system.controller;

import com.kylin.common.Result.Result;
import com.kylin.common.annotation.SysLog;
import com.kylin.common.base.controller.BaseController;
import com.kylin.common.utils.*;
import com.kylin.modules.system.entity.DictData;
import com.kylin.modules.system.service.DictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 数据字典接口
 * @author: kylin
 * @create: 2018-02-01 10:35
 **/
@Api(tags = {"数据字典接口"})
@RestController
@RequestMapping("/dict/data")
public class DictDataController extends BaseController {

    @Autowired
    private DictDataService dictDataService;

    @ApiOperation(value = "获取列表")
    @PostMapping("/list")
    @RequiresPermissions("sys:value:list")
    public Result list(Query query) {
        Map<String, Object> conditionMap = BeanMap.objectToMap(query);
        int tatal = dictDataService.queryTotal(conditionMap);
        List<DictData> list = dictDataService.queryList(conditionMap);

        PageUtils pageUtils = new PageUtils(list, tatal, query.getPageSize(), query.getPageNum());
        return Result.success(pageUtils);
    }

    @PostMapping("/info")
    public Result info(Long id) {
        DictData dictData = dictDataService.queryObject(id);
        return Result.success(dictData);
    }

    @SysLog("新增字典")
    @PostMapping("/save")
    public Result save(DictData dictData) {
        dictData.setUserId(getUserId());
        dictData.setCreateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        dictDataService.save(dictData);
        return Result.success();
    }

    /**
     * 修改字典
     */
    @SysLog("修改字典")
    @PostMapping("/update")
    public Result update(DictData dictData) {
        dictData.setUserId(getUserId());
        dictData.setUpdateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

        dictDataService.update(dictData);
        return Result.success();
    }

    /**
     * 删除字典
     */
    @SysLog("删除字典")
    @RequestMapping("/delete")
    public Result delete(String id) {
        Long[] idsArr = ArrayUtil.strToLongArr(id);
        dictDataService.deleteBatch(idsArr);
        return Result.success();
    }

    /**
     * 根据目录编号查询字典明细
     */
    @PostMapping("/select")
    public Result select(Query query) {
        Map<String, Object> params = BeanMap.objectToMap(query);
        List<DictData> list = dictDataService.queryList(params);

        return Result.success(list);
    }
}
