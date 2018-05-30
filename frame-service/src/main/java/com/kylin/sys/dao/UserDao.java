package com.kylin.sys.dao;

import com.kylin.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:  系统用户dao
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:51
 */
@Mapper
public interface UserDao extends BaseDao<User> {

    /**
     * 根据用户名查询有效的用户
     * @param username
     * @return
     */
    User queryByUserame(String username);
}
