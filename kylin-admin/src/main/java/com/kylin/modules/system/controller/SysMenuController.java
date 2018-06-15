package com.kylin.modules.system.controller;

import com.kylin.core.Result.Result;
import com.kylin.core.annotation.SysLog;
import com.kylin.common.base.controller.BaseController;
import com.kylin.core.exception.RRException;
import com.kylin.core.utils.Constant;
import com.kylin.modules.system.entity.SysMenu;
import com.kylin.modules.shiro.service.ShiroService;
import com.kylin.modules.system.service.SysMenuService;
import com.kylin.modules.system.service.SysRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 菜单管理
 * @author: kylin
 * @create: 2018-01-31 9:52
 **/
@Api(tags = {"菜单管理接口"})
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 导航菜单
     */
    @ApiOperation(value = "获取导航菜单列表")
    @PostMapping("/nav")
    public Result nav() {
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        return Result.success(menuList);
    }
    @PostMapping("/perms")
    public Result permission() {
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        return Result.success(permissions);
    }

    /**
     * 所有菜单列表(不包含按钮)
     */
    @ApiOperation(value = "获取所有菜单列表")
    @PostMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public Result list() {
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        return Result.success(menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ApiOperation(value = "选择菜单(添加、修改菜单)")
    @PostMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result select(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryList(null);
        //查询角色拥有的菜单id
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        for (SysMenu menu:menuList){
            for (Long menuId:menuIdList) {
                if (menu.getMenuId() == menuId){
                    menu.setChecked(true);
                }
            }
        }
        return Result.success(menuList);
    }

    /**
     * 菜单信息
     */
    @ApiOperation(value = "获取菜单信息")
    @PostMapping("/info")
    @RequiresPermissions("sys:menu:info")
    public Result info(Long menuId) {
        SysMenu menu = sysMenuService.queryObject(menuId);
        return Result.success(menu);
    }

    /**
     * 按钮列表
     *
     * @param menuId
     * @return
     */
    @PostMapping("/btn")
    public Result btnList(Long menuId) {
        List<SysMenu> btnList = sysMenuService.queryBtn(menuId);
        return Result.success(btnList);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存菜单")
    @SysLog("保存菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public Result save(SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return Result.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改菜单")
    @SysLog("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public Result update(SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.update(menu);

        return Result.success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除菜单")
    @SysLog("删除菜单")
    @GetMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(long menuId) {

        sysMenuService.deleteBatch(new Long[]{menuId});

        return Result.success();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getMenuName())) {
            throw new RRException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if (menu.getMenuType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getMenuUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getMenuType();
        }

        //目录、菜单
        if (menu.getMenuType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getMenuType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getMenuType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
