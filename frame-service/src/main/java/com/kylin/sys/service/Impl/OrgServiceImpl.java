package com.kylin.sys.service.Impl;

import com.kylin.sys.dao.OrgDao;
import com.kylin.sys.entity.Org;
import com.kylin.sys.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<Org> querylist(Map<String,Object> map) {
        return orgDao.queryList(map);
    }

    @Override
    public void save(Org org) {
        orgDao.save(org);
    }
}
