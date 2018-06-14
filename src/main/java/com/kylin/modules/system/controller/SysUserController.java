package com.kylin.modules.system.controller;

import com.kylin.common.Result.Result;
import com.kylin.common.annotation.SysLog;
import com.kylin.common.base.controller.BaseController;
import com.kylin.common.utils.*;
import com.kylin.common.validator.Assert;
import com.kylin.common.validator.ValidatorUtils;
import com.kylin.common.validator.group.AddGroup;
import com.kylin.common.validator.group.UpdateGroup;
import com.kylin.modules.system.entity.SysUser;
import com.kylin.modules.system.service.SysDeptService;
import com.kylin.modules.system.service.SysUserRoleService;
import com.kylin.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户管理controller
 * @author: kylin
 * @create: 2018-01-30 14:47
 **/
@RestController
@RequestMapping("sys/user")
@Api(tags ={"用户管理接口"})
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation(value = "获取用户列表")
    @PostMapping("/list")
    @RequiresPermissions("sys:user:list")
    public Result list(Query query){
        Map<String,Object> conditionMap = BeanMap.objectToMap(query);
        Long queryDeptId = query.getDeptId();

        //如果父id==0则不用在查询，直接显示所有用户
        if (queryDeptId != null){
            Long parentId = sysDeptService.queryObject(queryDeptId).getParentId();
            if (parentId != 0){
                String childDeptIds = sysDeptService.getSubDeptIdList(query.getDeptId());
                conditionMap.put("childDeptIds",childDeptIds);
            }
        }
        List<SysUser> userList = sysUserService.queryList(conditionMap);
        int total = sysUserService.queryTotal(conditionMap);

        Map<String,Object> map = new HashMap<>();
        map.put("list",userList);
        map.put("total",total);
        return Result.success(map);
    }

    @ApiOperation(value = "获取登录的用户信息")
    @GetMapping("/login-info")
    public Result loginInfo() {
        return Result.success(getUser());
    }

    @ApiOperation(value = "修改密码")
    @SysLog("修改密码")
    @PostMapping("/password")
    public Result password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        password = new Sha256Hash(password, getUser().getSalt()).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return Result.failure("原密码不正确");
        }

        return Result.success();
    }

    @ApiOperation(value = "根据Id获取用户信息")
    @PostMapping("/info")
    @RequiresPermissions("sys:user:info")
    public Result info(Long userId) {
        SysUser user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        user.setRoleIdsStr(StringUtils.join(roleIdList,","));

        return Result.success(user);
    }

    @ApiOperation(value = "保存用户")
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(SysUser user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.save(user);

        return Result.success();
    }

    @ApiOperation(value = "修改用户")
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(SysUser user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        List roleIdList =Arrays.asList(user.getRoleIdsStr().split(","));
        user.setCreateUserId(getUserId());
        user.setRoleIdList(roleIdList);
        sysUserService.update(user);

        return Result.success();
    }

    @ApiOperation(value = "删除用户")
    @SysLog("删除用户")
    @GetMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete( String userId) {
        Long[] userIds = ArrayUtil.strToLongArr(userId);
        if (ArrayUtils.contains(userIds, 1L)) {
            return Result.failure("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return Result.failure("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return Result.success();
    }
}
