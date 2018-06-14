package com.kylin.modules.scheduler.utils;

import com.kylin.common.exception.RRException;
import com.kylin.common.utils.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 执行定时任务
 * @author: kylin
 * @create: 2018-02-06 15:08
 **/
public class ScheduleRunnable implements Runnable {
    private Object target;
    private Method method;
    private String patams;

    public ScheduleRunnable(String beanName, String methodName, String patams) throws NoSuchMethodException {
        this.target = SpringContextUtils.getBean(beanName);
        this.patams = patams;

        if (StringUtils.isNotBlank(patams)){
            this.method = target.getClass().getDeclaredMethod(methodName,String.class);
        }else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(patams)){
                method.invoke(target,patams);
            }else {
                method.invoke(target);
            }
        } catch (IllegalAccessException e) {
            throw new RRException("执行定时任务失败", e);
        } catch (InvocationTargetException e) {
            throw new RRException("执行定时任务失败", e);
        }
    }
}














