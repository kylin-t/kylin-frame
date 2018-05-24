package com.kylin.sys.controller;

import com.kylin.Result.Result;
import com.kylin.sys.entity.Role;
import com.kylin.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Result list(@RequestParam Map<String, Object> map) {
        List<Role> list = roleService.queryList(map);
        return Result.success(list);
    }
}
