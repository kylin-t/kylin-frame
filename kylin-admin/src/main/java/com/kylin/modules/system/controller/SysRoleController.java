package com.kylin.modules.system.controller;

import com.kylin.core.Result.Result;
import com.kylin.core.annotation.SysLog;
import com.kylin.common.base.controller.BaseController;
import com.kylin.core.utils.*;
import com.kylin.core.validator.ValidatorUtils;
import com.kylin.modules.system.entity.SysRole;
import com.kylin.modules.system.service.SysRoleMenuService;
import com.kylin.modules.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 角色管理
 * @author: kylin
 * @create: 2018-01-31 9:17
 **/
@Api(tags = {"角色管理接口"})
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @ApiOperation(value = "获取角色列表")
    @PostMapping("/list")
    @RequiresPermissions("sys:role:list")
    public Result list(Query query){
        Map<String,Object> conditionMap = BeanMap.objectToMap(query);
        //如果不是超级管理员，则只查询和自己同级或更低的角色列表
        if(getUserId() != Constant.SUPER_ADMIN){
            conditionMap.put("userId", getUserId());
        }
        //查询列表数据
        List<SysRole> list = sysRoleService.queryList(conditionMap);
        int total = sysRoleService.queryTotal(conditionMap);

        PageUtils pageUtil = new PageUtils(list, total, query.getPageSize(), query.getPageNum());

        return Result.success(pageUtil);
    }
    @ApiOperation(value = "获取角色列表",notes = "无参数")
    @PostMapping("/select")
    public Result select(){
        Map<String, Object> map = new HashMap<>();
        if(getUserId() != Constant.SUPER_ADMIN){
            map.put("userId", getUserId());
        }
        List<SysRole> list = sysRoleService.queryList(map);

        return Result.success(list);
    }

    @ApiOperation(value = "角色信息")
    @PostMapping("/info")
    @RequiresPermissions("sys:role:info")
    public Result info(Long roleId){
        SysRole role = sysRoleService.queryObject(roleId);
        return Result.success(role);
    }

    @ApiOperation(value = "保存角色")
    @SysLog("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public Result save(SysRole role){
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        sysRoleService.save(role);

        return Result.success();
    }

    @ApiOperation(value = "修改角色")
    @SysLog("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public Result update(SysRole role){
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        sysRoleService.update(role);

        return Result.success();
    }

    @SysLog("配置菜单权限")
    @PostMapping("/menu-auth")
    public Result menuAuth(Long roleId,String menuIds){

        Long[] longMneuIdArr = ArrayUtil.strToLongArr(menuIds);

        sysRoleMenuService.saveOrUpdate(roleId,longMneuIdArr);

        return Result.success();
    }

    @ApiOperation(value = "删除角色")
    @SysLog("删除角色")
    @GetMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(String roleId){
        Long[] roleIdArr = ArrayUtil.strToLongArr(roleId);
        sysRoleService.deleteBatch(roleIdArr);

        return Result.success();
    }
    @RequestMapping("/select-user")
    public R selectUser(){
        Map<String, Object> map = new HashMap<>();
        if(getUserId() != Constant.SUPER_ADMIN){
            map.put("deptId", getDeptId());
        }
        List<SysRole> list = sysRoleService.queryList(map);

        return R.ok().put("list", list);
    }
}
