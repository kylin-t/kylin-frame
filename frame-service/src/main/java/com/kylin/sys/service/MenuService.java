package com.kylin.sys.service;


import com.kylin.sys.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * @Description:  菜单管理service
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 16:02
 */
public interface MenuService {

    List<Menu> queryList(Map<String,Object> map);

    /**
     * 根据父菜单id查询所有的子菜单列表
     * @param parentId 父菜单id
     * @return
     */
    List<Menu> queryListByParentId(Long parentId);

    /**
     * 根据父菜单id查询用户拥有的子菜单列表
     * @param parentId 父菜单id
     * @param menuId 用户菜单id列表
     * @return
     */
    List<Menu> queryListByParentId(Long parentId,List<Long> menuId);

    /**
     * 获取用户所拥有的菜单列表
     * @param userId
     * @return
     */
    List<Menu> getUserMenuList(Long userId);

    void save(Menu menu);

    Menu queryObject(Long menuId);

    void update(Menu menu);

}
