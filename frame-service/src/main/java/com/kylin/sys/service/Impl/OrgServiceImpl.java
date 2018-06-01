package com.kylin.sys.service.Impl;

import com.kylin.sys.dao.OrgDao;
import com.kylin.sys.entity.Org;
import com.kylin.sys.service.OrgService;
import com.kylin.utils.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 16:06
 */
@Service("orgService")
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgDao orgDao;

    @Override
    public List<Org> querylist(Filter filter) {
        return orgDao.queryList(filter);
    }

    @Override
    public void save(Org org) {
        orgDao.save(org);
    }
}
