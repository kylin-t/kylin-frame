package com.kylin.sys.controller;

import com.kylin.Result.Result;
import com.kylin.sys.entity.User;
import com.kylin.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/05/21 17:56
 */
@RestController
@RequestMapping("sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public Result list(Map<String,Object> map){
        List<User> list = userService.queryList(map);
        return Result.success(list);
    }
}
