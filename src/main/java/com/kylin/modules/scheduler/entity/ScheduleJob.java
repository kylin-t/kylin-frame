package com.kylin.modules.scheduler.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 定时任务实体类
 * @author: kylin
 * @create: 2018-02-06 11:15
 **/
public class ScheduleJob implements Serializable {
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
     * 任务id
     */
    private Long jobId;
    /**
     * spring bean名称
     */
    @NotBlank(message = "bean名称不能为空")
    private String beanName;
    /**
     * 方法名称
     */
    @NotBlank(message = "方法名称不能为空")
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * 表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
    /**
     * 任务状态 0--正常  1--暂停
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 任务创建时间
     */
    private Date createTime;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
