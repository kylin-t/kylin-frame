package com.kylin.shiro.realm;

import com.kylin.sys.entity.Menu;
import com.kylin.sys.entity.Role;
import com.kylin.sys.entity.User;
import com.kylin.sys.service.MenuService;
import com.kylin.sys.service.RoleService;
import com.kylin.sys.service.UserService;
import com.kylin.utils.Constant;
import com.kylin.utils.ShiroUtils;
import com.kylin.utils.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author kylin
 * Description  shiro认证
 * @Date Created in 2018/05/25 17:21
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        if (user != null) {
            //根据用户id查询该用户所有的角色,并加入到shiro的SimpleAuthorizationInfo
            List<Role> roles = roleService.queryByUserId(user.getUserId(), Constant.STATUS.NORMAL.getValue());
            for (Role role : roles) {
                info.addRole(String.valueOf(role.getRoleId()));
            }
            //查询所有该用户授权菜单，并加到shiro的SimpleAuthorizationInfo 做菜单按钮权限控件

            Set<String> permissions = new HashSet<>();
            List<Menu> menuList = null;
            //超级管理员拥有最高权限
            if (Constant.SUPER_ADMIN == user.getUserId()) {
                menuList = menuService.queryList(new HashMap<>());
            } else {
                //普通帐户权限查询
                menuList = menuService.queryByUserId(user.getUserId());
            }
            for (Menu menu : menuList) {
                if (StringUtils.isNotEmpty(menu.getPermission())) {
                    permissions.add(menu.getPermission());
                }
            }
            info.addStringPermissions(permissions);
        }
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userLoginName = (String) token.getPrincipal();
        User user = userService.queryByUserame(userLoginName);
        if (user == null) {
            throw new AuthenticationException("帐号密码错误");
        }
        if (Constant.STATUS.DISABLED.getValue() == user.getStatus()) {
            throw new LockedAccountException("帐号被禁用,请联系管理员!");
        }
//        //用户对应的机构集合
//        List<String> baidList = userService.queryBapidByUserIdArray(user.getId());
//        //用户对应的部门集合
//        List<String> bapidList = userService.queryBaidByUserIdArray(user.getId());
//        user.setBapidList(bapidList);
//        user.setBaidList(baidList);
        SimpleAuthenticationInfo sainfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return sainfo;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.algorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
