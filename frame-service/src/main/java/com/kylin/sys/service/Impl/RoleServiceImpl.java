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
        roleDao.save(role);
    }

    @Override
    public Role queryObject(Long roleId) {
        return roleDao.queryObject(roleId);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void deleteBatch(String roleIds) {
        String[] strIds = roleIds.split(",");
        Long[] longIds = new Long[strIds.length];
        for (int i = 0;i<strIds.length;i++){
            longIds[i] = new Long(strIds[i]);
        }
        roleDao.deleteBatch(longIds);
    }

    @Override
    public List<Role> queryByUserId(Long userId,Integer status) {
        return roleDao.queryByUserId(userId,status);
    }
}
