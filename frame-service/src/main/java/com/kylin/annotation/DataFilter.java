package com.kylin.annotation;

import java.lang.annotation.*;

/**
 * @Description: 数据过滤
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/06/06 23:35
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
