package com.kylin.exception;

import com.kylin.enums.ResultCode;

/**
 * @Author kylin
 * Description  远程访问异常
 * @Date Created in 2018/05/21 11:34
 */
public class RemoteAccessException extends BusinessException {

    public RemoteAccessException() {
        super();
    }

    public RemoteAccessException(Object data) {
        super.data = data;
    }

    public RemoteAccessException(ResultCode resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public RemoteAccessException(String msg) {
        super(msg);
    }

    public RemoteAccessException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
