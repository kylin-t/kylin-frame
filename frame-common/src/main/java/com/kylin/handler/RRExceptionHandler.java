package com.kylin.handler;

import com.kylin.Result.Result;
import com.kylin.enums.ResultCode;
import com.kylin.exception.RRException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/05/31 11:36
 */
@RestControllerAdvice
public class RRExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public Result handleRRException(RRException e){
        return Result.failure(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return Result.failure("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return Result.failure("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        logger.error(e.getMessage(), e);
        return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
    }
}
