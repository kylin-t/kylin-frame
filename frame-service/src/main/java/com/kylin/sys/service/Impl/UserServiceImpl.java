package com.kylin.sys.service.Impl;

import com.kylin.sys.service.UserRoleService;
import com.kylin.utils.Filter;
import com.kylin.utils.ShiroUtils;
import com.kylin.sys.dao.UserDao;
import com.kylin.sys.entity.User;
import com.kylin.sys.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 16:03
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User queryObject(Long id) {
        return userDao.queryObject(id);
    }

    @Override
    public List<User> queryList(Filter filter) {
        return userDao.queryList(filter);
    }

    @Override
    public int queryTotal(Filter filter) {
        return userDao.queryTotal(filter);
    }

    @Override
    @Transient
    public void save(User user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        userDao.save(user);

        //保存用户与角色关系
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteBatch(Long[] ids) {
        userRoleService.deleteBatch(ids);
        userDao.deleteBatch(ids);
    }

    @Override
    public User queryByUserame(String username) {
        return userDao.queryByUserame(username);
    }
}
