package com.kylin.sys.service;


import com.kylin.sys.entity.Org;
import com.kylin.utils.Filter;

import java.util.List;

/**
 * @Description:  组织机构service
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:59
 */
public interface OrgService {

    List<Org> querylist(Filter filter);

    void save(Org org);
}
