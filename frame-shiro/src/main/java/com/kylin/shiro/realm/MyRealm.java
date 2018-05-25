package com.kylin.shiro.realm;

import com.kylin.sys.entity.Menu;
import com.kylin.sys.entity.Role;
import com.kylin.sys.entity.User;
import com.kylin.sys.service.MenuService;
import com.kylin.sys.service.RoleService;
import com.kylin.sys.service.UserService;
import com.kylin.utils.Constant;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
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
public class MyRealm {

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
            List<Menu> menu = null;
            //超级管理员拥有最高权限
            if (Constant.SUPER_ADMIN == user.getUserId()) {
                menu = menuService.queryList(new HashMap<>());
            } else {
                //普通帐户权限查询
                menu = menuService.queryByUserId(user.getId());
            }
            for (MenuEntity menuEntity : menuEntities) {
                if (StringUtils.isNotEmpty(menuEntity.getPermission())) {
                    permissions.add(menuEntity.getPermission());
                }
            }
            info.addStringPermissions(permissions);
        }
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userLoginName = (String) token.getPrincipal();
        UserEntity user = userService.queryByLoginName(userLoginName);
        if (user == null) {
            throw new AuthenticationException("帐号密码错误");
        }
        if (Constant.ABLE_STATUS.NO.getValue().equals(user.getStatus())) {
            throw new LockedAccountException("帐号被禁用,请联系管理员!");
        }
        //用户对应的机构集合
        List<String> baidList = userService.queryBapidByUserIdArray(user.getId());
        //用户对应的部门集合
        List<String> bapidList = userService.queryBaidByUserIdArray(user.getId());
        user.setBapidList(bapidList);
        user.setBaidList(baidList);
        SimpleAuthenticationInfo sainfo = new SimpleAuthenticationInfo(user, user.getPassWord(), ByteSource.Util.bytes(user.getSalt()), getName());
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
