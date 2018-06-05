package com.kylin.sys.controller;

import com.kylin.Result.Result;
import com.kylin.sys.entity.User;
import com.kylin.sys.service.UserRoleService;
import com.kylin.sys.service.UserService;
import com.kylin.utils.Condition;
import com.kylin.utils.PageUtil;
import com.kylin.utils.StringUtil;
import org.apache.commons.beanutils.BeanMap;
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
    public Result list(Condition condition){
        User user = getUser();
        Map<String,Object> conditionMap=new BeanMap(condition);
        List<User> list = userService.queryList(conditionMap);
        int total = userService.queryTotal(conditionMap);

        PageUtil pageUtil = new PageUtil(list,total,condition.getPageSize(),condition.getPageNum());

        return Result.success(pageUtil);
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
        Long[] longUserIdArray = StringUtil.strToLongAttr(userId);
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
