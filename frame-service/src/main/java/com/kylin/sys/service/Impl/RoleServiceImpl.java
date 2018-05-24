package com.kylin.sys.service.Impl;

import com.kylin.sys.dao.RoleDao;
import com.kylin.sys.entity.Role;
import com.kylin.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 16:03
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> queryList(Map<String, Object> map) {
        return roleDao.queryList(map);
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public Role queryObject(Long roleId) {
        return null;
    }

    @Override
    public void update(Role role) {

    }
}
