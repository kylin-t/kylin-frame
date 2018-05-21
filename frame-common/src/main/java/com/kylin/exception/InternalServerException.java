package com.kylin.exception;

/**
 * @Author kylin
 * Description  内部服务异常
 * @Date Created in 2018/05/21 11:32
 */
public class InternalServerException extends BusinessException {

    public InternalServerException() {
        super();
    }

    public InternalServerException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InternalServerException(String msg, Throwable cause, Object... objects) {
        super(msg, cause, objects);
    }

    public InternalServerException(String msg) {
        super(msg);
    }

    public InternalServerException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
