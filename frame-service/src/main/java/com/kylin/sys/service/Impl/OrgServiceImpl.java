package com.kylin.sys.service.Impl;

import com.kylin.sys.dao.OrgDao;
import com.kylin.sys.entity.Org;
import com.kylin.sys.service.OrgService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Long> getChildOrgIdList(Long parentId) {
        return orgDao.getChildOrgIdList(parentId);
    }

    @Override
    public String getOrgIdList(Long orgId) {
        //部门及子部门ID列表
        List<Long> orgIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> childOrgIdList = getChildOrgIdList(orgId);
        getOrgTreeList(childOrgIdList, orgIdList);

        //添加本部门
        orgIdList.add(orgId);

        String deptFilter = StringUtils.join(orgIdList, ",");
        return deptFilter;
    }

    /**
     * 递归
     */
    private void getOrgTreeList(List<Long> childOrgIdList, List<Long> orgIdList){
        for(Long orgId : childOrgIdList){
            List<Long> list = getChildOrgIdList(orgId);
            if(list.size() > 0){
                getOrgTreeList(list, orgIdList);
            }

            orgIdList.add(orgId);
        }
    }
}
