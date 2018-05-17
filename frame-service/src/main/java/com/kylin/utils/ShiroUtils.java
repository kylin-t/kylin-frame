package com.kylin.utils;

import com.kylin.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Description:  shiro工具类
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/05/03 22:15
 */
public class ShiroUtils {

    public final static String algorithmName = "SHA-256";

    /**
     * 加密散列次数
     */
    public static final int hashIterations= 1;

    public static String encodeSalt(String password,String salt){
        return new SimpleHash(algorithmName,password,salt,hashIterations).toString();
    }

    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static User getUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUser().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }

}
