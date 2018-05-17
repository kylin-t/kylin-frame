package com.kylin.sys.service;

import com.kylin.sys.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Description:  系统用户service
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:57
 */
public interface UserService {

    User queryObject(Long id);

    List<User> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(User user);

    void update(User user);

    void delete(String id);

    void deleteBatch(String[] ids);
}
