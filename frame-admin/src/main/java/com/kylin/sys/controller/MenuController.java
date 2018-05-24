package com.kylin.sys.controller;

import com.kylin.Result.Result;
import com.kylin.sys.entity.Menu;
import com.kylin.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author kylin
 * Description 菜单管理
 * @Date Created in 2018/05/17 15:47
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 导航菜单
     * @return
     */
    @RequestMapping("/nav")
    public Result nav(){
        List<Menu> menuList = menuService.getUserMenuList(1L);
        return Result.success(menuList);
    }
    @RequestMapping("save")
    public Result save( Menu menu){
        menuService.save(menu);
        return Result.success();
    }
    @RequestMapping("/info")
    public Result queryObject(Long menuId){
        Menu menu = menuService.queryObject(menuId);
        return Result.success(menu);
    }

    @RequestMapping("/update")
    public Result update(Menu menu){
        menuService.update(menu);
        return Result.success();
    }
}
