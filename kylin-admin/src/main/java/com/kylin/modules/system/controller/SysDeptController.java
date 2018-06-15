package com.kylin.modules.system.controller;

import com.kylin.core.Result.Result;
import com.kylin.core.annotation.SysLog;
import com.kylin.common.base.controller.BaseController;
import com.kylin.core.utils.Constant;
import com.kylin.core.utils.R;
import com.kylin.modules.system.entity.SysDept;
import com.kylin.modules.system.service.SysDeptService;
import com.kylin.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 部门管理接口
 * @author: kylin
 * @create: 2018-01-31 10:16
 **/
@Api(tags = {"部门管理接口"})
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @ApiOperation(value = "获取部门列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public Result list() {
//        Long userId = getUserId();
//        if (userId != Constant.SUPER_ADMIN) {
//            return Result.success(sysDeptService.getChildDepts(getDeptId()));
//        }
        return Result.success(sysDeptService.queryList(new HashMap<>()));
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @ApiOperation(value = "选择部门")
    @GetMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public R select() {
        Map<String, Object> map = new HashMap<>();
        String childNodes = sysDeptService.getSubDeptIdList(getDeptId());
        map.put("deptIds", childNodes);
        List<SysDept> deptList = sysDeptService.queryList(map);

        //添加一级部门
        if (getUserId() == Constant.SUPER_ADMIN) {
            SysDept root = new SysDept();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }

        return R.ok().put("deptList", deptList);
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @ApiOperation(value = "获取上级部门Id")
    @GetMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public R info() {
        if (getUserId() == Constant.SUPER_ADMIN) {
            return R.ok().put("deptId", 0);
        }
        SysDept dept = sysDeptService.queryObject(getDeptId());
        long deptId = dept.getParentId();


        return R.ok().put("deptId", deptId);
    }

    /**
     * 根据登录用户查询所在部门及下属所有部门
     *
     * @return
     */
    @GetMapping("/list-user")
    public List<SysDept> listUser() {
        if (getUserId() != Constant.SUPER_ADMIN) {
            return sysDeptService.getChildDepts(getDeptId());
        }
        return sysDeptService.getChildDepts(null);
    }

    /**
     * 信息
     */
    @ApiOperation(value = "获取部门信息")
    @PostMapping("/info")
    @RequiresPermissions("sys:dept:info")
    public Result info(Long deptId) {
        SysDept dept = sysDeptService.queryObject(deptId);

        return Result.success(dept);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存部门")
    @SysLog("保存部门")
    @PostMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public Result save(SysDept dept) {
        sysDeptService.save(dept);

        return Result.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改部门")
    @SysLog("修改部门")
    @PostMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public Result update(SysDept dept) {
        sysDeptService.update(dept);

        return Result.success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除部门")
    @SysLog("删除部门")
    @GetMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public Result delete(long deptId) {
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return Result.failure("请先删除子部门");
        }
        sysDeptService.delete(deptId);
        return Result.success();
    }
}
