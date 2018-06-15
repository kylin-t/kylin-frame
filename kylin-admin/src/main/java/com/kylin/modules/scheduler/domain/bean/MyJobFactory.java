//package com.kylin.modules.scheduler.domain.bean;
//
//import org.quartz.spi.TriggerFiredBundle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.scheduling.quartz.AdaptableJobFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @Description:
// * @author: kylin
// * @create: 2018-02-08 15:11
// **/
//@Component
//public class MyJobFactory extends AdaptableJobFactory {
//
//    @Autowired
//    private AutowireCapableBeanFactory capableBeanFactory;
//
//    @Override
//    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
//        Object jobInstance = super.createJobInstance(bundle);
//        capableBeanFactory.autowireBean(jobInstance); //这一步解决不能spring注入bean的问题
//        return jobInstance;
//    }
//
//}