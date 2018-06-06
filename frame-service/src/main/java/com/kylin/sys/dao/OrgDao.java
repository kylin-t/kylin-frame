package com.kylin.sys.dao;

import com.kylin.sys.entity.Org;

import java.util.List;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:52
 */
public interface OrgDao extends BaseDao<Org> {
    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> getChildOrgIdList(Long parentId);
}
