package com.kylin.modules.shiro.service.impl;

import com.kylin.core.utils.Constant;
import com.kylin.modules.system.dao.SysMenuDao;
import com.kylin.modules.system.dao.SysUserDao;
import com.kylin.modules.system.dao.SysUserTokenDao;
import com.kylin.modules.system.entity.SysMenu;
import com.kylin.modules.shiro.service.ShiroService;
import com.kylin.modules.system.entity.SysUser;
import com.kylin.modules.system.entity.SysUserToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @author: kylin
 * @create: 2018-01-31 10:07
 **/
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenu> menuList = sysMenuDao.queryList(new HashMap<>());
            permsList = new ArrayList<>(menuList.size());
            for(SysMenu menu : menuList){
                permsList.add(menu.getPermission());
            }
        }else{
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUser queryUser(Long userId) {
        return sysUserDao.queryObject(userId);
    }
}
