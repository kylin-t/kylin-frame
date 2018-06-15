package com.kylin.common.validator;

import com.kylin.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * @Description: 数据校验
 * @author: kylin
 * @create: 2018-01-30 10:06
 **/
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
