package com.kylin.modules.system.entity;

import java.io.Serializable;

/**
 * @Description: 数据字典
 * @author: kylin
 * @create: 2018-02-01 10:02
 **/
public class DictData implements Serializable {
    /**
     * 数据字典id(主键)
     */
    private Long id;
    /**
     * 存储值key
     */
    private String dataKey;
    /**
     * 显示值value
     */
    private String dataValue;
    /**
     * 字典分类key
     */
    private String typeKey;
    /**
     * 字典分类value
     */
    private String typeValue;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更改时间
     */
    private String updateTime;
    /**
     * 更改人id
     */
    private long userId;
    /**
     * 更改人用户名
     */
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
