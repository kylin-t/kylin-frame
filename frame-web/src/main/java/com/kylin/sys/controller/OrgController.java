package com.kylin.sys.controller;

import com.kylin.Result.Result;
import com.kylin.sys.entity.Org;
import com.kylin.sys.service.OrgService;
import com.kylin.utils.Condition;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/05/21 17:57
 */
@RestController
@RequestMapping("sys/org")
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;

    /**
     * 组织机构列表
     * @param condition
     * @return
     */
    @RequestMapping("/list")
    public Result queryList(Condition condition){
        List<Org> list = orgService.querylist(new BeanMap(condition));
        return  Result.success(list);
    }

    @RequestMapping("/save")
    public Result save(Org org){
        orgService.save(org);
        return Result.success();
    }

}
