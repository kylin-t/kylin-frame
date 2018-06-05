package com.kylin.sys.controller;

import com.kylin.Result.Result;
import com.kylin.sys.entity.Role;
import com.kylin.sys.service.RoleService;
import com.kylin.utils.Condition;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/05/21 17:56
 */
@RestController
@RequestMapping("sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public Result list(Condition condition) {
        Map<String,Object> conditionMap = new BeanMap(condition);
        List<Role> list = roleService.queryList(conditionMap);
        int total = roleService.queryTotal(conditionMap);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return Result.success(map);
    }

    @RequestMapping("/save")
    public Result save(Role role){
        roleService.save(role);
        return Result.success();
    }
    @GetMapping("/delete")
    public Result delete(String roleId){
        roleService.deleteBatch(roleId);
        return Result.success();
    }

    /**
     * 根据ID查询单个对象
     * @return
     */
    @RequestMapping("/info")
    public Result info(Long roleId){
        Role role = roleService.queryObject(roleId);
        return Result.success(role);
    }

    /**
     * 更新
     * @param role
     * @return
     */
    @RequestMapping("/update")
    public Result update(Role role){
        roleService.update(role);
        return Result.success();
    }
}
