package com.kylin.validator.group;

import javax.validation.GroupSequence;

/**
 * @Description:  定义校验顺序   如果AddGroup校验失败，则UpdateGroup不会再校验
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 00:01
 */
@GroupSequence({AddGroup.class,Group.class})
public interface Group {
}
