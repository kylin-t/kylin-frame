package com.kylin.exception;

import com.kylin.enums.ResultCode;

/**
 * @Author kylin
 * Description  权限异常
 * @Date Created in 2018/05/21 11:33
 */
public class PermissionForbiddenException extends BusinessException {

    public PermissionForbiddenException() {
        super();
    }

    public PermissionForbiddenException(Object data) {
        super.data = data;
    }

    public PermissionForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }

    public PermissionForbiddenException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public PermissionForbiddenException(String msg) {
        super(msg);
    }

    public PermissionForbiddenException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
