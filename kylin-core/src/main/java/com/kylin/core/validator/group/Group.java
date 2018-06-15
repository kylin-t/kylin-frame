package com.kylin.core.validator.group;

import javax.validation.GroupSequence;

/**
 * @Description: 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @author: kylin
 * @create: 2018-01-30 10:06
 **/
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
