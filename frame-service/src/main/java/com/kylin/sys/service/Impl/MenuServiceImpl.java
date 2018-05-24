package com.kylin.sys.service.Impl;

import com.kylin.sys.dao.MenuDao;
import com.kylin.sys.entity.Menu;
import com.kylin.sys.service.MenuService;
import com.kylin.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:  菜单管理
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 16:07
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> queryListByParentId(Long parentId) {
        return menuDao.queryListByParentId(parentId);
    }

    @Override
    public List<Menu> queryListByParentId(Long parentId, List<Long> menuIdList) {
        List<Menu> menuList = queryListByParentId(parentId);
        if (menuIdList == null){
            return menuList;
        }
        List<Menu> userMenuList = new ArrayList<>();
        for (Menu menu: menuList){
            if (menuList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<Menu> getUserMenuList(Long userId) {
        if (userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }
        return null;
    }

    /**
     * 获取所有菜单列表
     * @param menuIdList
     * @return
     */
    private List<Menu> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
//        List<Menu> menuList = queryListByParentId(0L,menuIdList);
        //递归获取子菜单
//        getChildMenuList(menuList,menuIdList);
        List<Menu> menuList = menuDao.queryList(new HashMap<>());
        return menuList;
    }

    /**
     * 递归
     * @param menuList
     * @param menuIdList
     * @return
     */
    private List<Menu> getChildMenuList(List<Menu> menuList,List<Long> menuIdList){
       List<Menu> childMenuList = new ArrayList<>();

       for (Menu menu : menuList){
           if (menu.getMenuType() == 0){
               menu.setChildList(getChildMenuList(queryListByParentId(menu.getMenuId(),menuIdList),menuIdList));
           }
           childMenuList.add(menu);
       }
       return childMenuList;
    }

    @Override
    public void save(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public Menu queryObject(Long menuId) {
        return menuDao.queryObject(menuId);
    }

    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }
}
