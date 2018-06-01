package com.kylin.sys.controller;

import com.kylin.Result.Result;
import com.kylin.sys.entity.User;
import com.kylin.sys.service.UserRoleService;
import com.kylin.sys.service.UserService;
import com.kylin.utils.Filter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/list")
    public Result list(Filter filter){
        User user = getUser();
        List<User> list = userService.queryList(filter);
        int total = userService.queryTotal(filter);
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        return Result.success(map);
    }

    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(User user) {
        userService.save(user);
        return Result.success();
    }

    @GetMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(String userId) {
        String[] strUserIdArray = userId.split(",");
        Long[] longUserIdArray = new Long[strUserIdArray.length];
        for (int i = 0;i<strUserIdArray.length;i++){
            longUserIdArray[i] = new Long(strUserIdArray[i]);
        }

        if (ArrayUtils.contains(longUserIdArray, 1L)) {
            return Result.failure("系统管理员不能删除");
        }

        if (ArrayUtils.contains(longUserIdArray, getUserId())) {
            return Result.failure("当前用户不能删除");
        }

        userService.deleteBatch(longUserIdArray);

        return Result.success();
    }

    @RequestMapping("/info")
    @RequiresPermissions("sys:user:info")
    public Result info(Long userId) {
        User user = userService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        user.setRoleIdStr(StringUtils.join(roleIdList,","));

        return Result.success(user);
    }
}
