package com.kylin.sys.dao;

import com.kylin.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);
}
