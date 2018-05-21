package com.kylin.exception;

import com.kylin.enums.ResultCode;

/**
 * @Author kylin
 * Description  数据已存在异常
 * @Date Created in 2018/05/21 11:29
 */
public class DataConflictException extends BusinessException {

    public DataConflictException() {
        super();
    }

    public DataConflictException(Object data) {
        super.data = data;
    }

    public DataConflictException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataConflictException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataConflictException(String msg) {
        super(msg);
    }

    public DataConflictException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
