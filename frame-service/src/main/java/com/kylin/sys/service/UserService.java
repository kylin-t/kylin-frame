package com.kylin.sys.service;

import com.kylin.sys.entity.User;
import com.kylin.utils.Filter;

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

    List<User> queryList(Filter filter);

    int queryTotal(Filter filter);

    void save(User user);

    void update(User user);

    void delete(String id);

    void deleteBatch(Long[] ids);

    User queryByUserame(String username);
}
