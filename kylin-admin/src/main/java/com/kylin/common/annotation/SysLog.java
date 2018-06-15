package com.kylin.common.annotation;

import java.lang.annotation.*;

/**
 * @Description: 系统日志注解
 * @author: kylin
 * @create: 2018-01-30 10:03
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
