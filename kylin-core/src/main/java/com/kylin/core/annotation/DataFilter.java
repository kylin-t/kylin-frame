package com.kylin.core.annotation;

import java.lang.annotation.*;

/**
 * @Author kylin
 * Description 数据过滤
 * @Date Created in 2018/06/07 17:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**  表的别名 */
    String tableAlias() default  "";

    /**  true：没有本部门数据权限，也能查询本人数据 */
    boolean user() default true;
}