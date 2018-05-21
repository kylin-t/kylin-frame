package com.kylin.exception;

/**
 * @Author kylin
 * Description  用户未登录异常
 * @Date Created in 2018/05/21 11:35
 */
public class UserNotLoginException extends BusinessException {

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

    public UserNotLoginException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
