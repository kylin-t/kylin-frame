package com.kylin.sys.service.Impl;

import com.kylin.sys.dao.UserRoleDao;
import com.kylin.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 16:08
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }

        //先删除用户与角色关系
        userRoleDao.delete(userId);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        userRoleDao.save(map);
    }

    @Override
    public void deleteBatch(Long[] userId) {
        userRoleDao.deleteBatch(userId);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return userRoleDao.queryRoleIdList(userId);
    }
}
