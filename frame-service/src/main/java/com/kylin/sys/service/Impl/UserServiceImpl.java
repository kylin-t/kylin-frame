package com.kylin.sys.service.Impl;

import com.kylin.sys.dao.UserDao;
import com.kylin.sys.entity.User;
import com.kylin.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
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

    @Override
    public User queryObject(Long id) {
        return userDao.queryObject(id);
    }

    @Override
    public List<User> queryList(Map<String, Object> map) {
        return userDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    @Transient
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteBatch(String[] ids) {

    }
}
