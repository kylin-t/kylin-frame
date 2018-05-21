package com.kylin.exception;

import com.kylin.enums.ResultCode;

/**
 * @Author kylin
 * Description  数据没找到异常
 * @Date Created in 2018/05/21 11:31
 */
public class DataNotFoundException extends BusinessException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(Object data) {
        super();
        super.data = data;
    }

    public DataNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataNotFoundException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
