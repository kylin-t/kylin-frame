package com.kylin.sys.service;

import com.kylin.sys.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 17:08
 */
public class UserServiceTest {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Before
    public void before() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-mybatis.xml"});
        userService = (UserService) ac.getBean("userService");
    }

    @Test
    public void addUser(){
        User u = new User();
        u.setUsername("kylin");
        userService.save(u);
    }
    @Test
    public void queryList(){
        Map<String,Object> map = new HashMap<>();
        List<User> list = userService.queryList(map);
        for (User user:
             list) {
            System.out.println(user);
        }
    }
}