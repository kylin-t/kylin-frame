package com.kylin.modules.system.service.impl;

import com.kylin.common.exception.RRException;
import com.kylin.common.utils.Constant;
import com.kylin.common.utils.DateUtils;
import com.kylin.modules.system.dao.SysRoleDao;
import com.kylin.modules.system.dao.SysUserRoleDao;
import com.kylin.modules.system.entity.SysRole;
import com.kylin.modules.system.service.SysRoleMenuService;
import com.kylin.modules.system.service.SysRoleService;
import com.kylin.modules.system.service.SysUserRoleService;
import com.kylin.modules.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public SysRole queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    @Override
    public List<SysRole> queryList(Map<String, Object> map) {
        if (map.get("userId")!=null){
            //查询当前用户的角色id列表
            List<Long> roleIdList = sysUserRoleDao.queryRoleIdList((Long) map.get("userId"));
            Long maxRoleId = Collections.max(roleIdList);
            Integer maxLevelNum = sysRoleDao.queryObject(maxRoleId).getLevelNum();
            //只能获取比当前角色级别低或同级的列表
            if (maxRoleId != null) {
                map.put("maxLevelNum",maxLevelNum);
            }
        }
        return sysRoleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysRole role) {
        role.setCreateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        sysRoleDao.save(role);

        //检查权限是否越权
        //		checkPrems(role);

        //保存角色与菜单关系
        //		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void update(SysRole role) {
        sysRoleDao.update(role);

        //检查权限是否越权
        //		checkPrems(role);
        //
        //		//更新角色与菜单关系
        //		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        sysRoleDao.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return sysRoleDao.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRole role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new RRException("新增角色的权限，已超出你的权限范围");
        }
    }
}
