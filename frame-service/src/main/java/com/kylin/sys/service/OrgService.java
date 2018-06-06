package com.kylin.sys.service;


import com.kylin.sys.entity.Org;

import java.util.List;
import java.util.Map;

/**
 * @Description:  组织机构service
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:59
 */
public interface OrgService {

    List<Org> querylist(Map<String,Object> map);

    void save(Org org);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> getChildOrgIdList(Long parentId);

    /**
     * 获取子部门ID(包含本部门ID)，用于数据过滤
     */
    String getOrgIdList(Long orgId);
}
