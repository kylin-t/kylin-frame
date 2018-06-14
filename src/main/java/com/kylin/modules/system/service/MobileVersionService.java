package com.kylin.modules.system.service;

import com.kylin.modules.system.entity.MobileVersion;

import java.util.List;
import java.util.Map;

/**
 * @Author hy
 * @Date 2018/1/11 17:46
 */
public interface MobileVersionService {

    MobileVersion queryObject(Long versionId);

    List<MobileVersion> queryList(Map<String, Object> map);

    List<MobileVersion> queryListParentID(Map<String, Object> map);

    List<MobileVersion> queryReamrkByVersionId(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(MobileVersion mobileVersion);

    void update(MobileVersion mobileVersion);

    void deleteBatch(String[] versionIds);
}
