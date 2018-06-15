package com.kylin.modules.system.dao;


import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.MobileVersion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author hy
 * @Date 2018/1/11 17:38
 */
@Mapper
public interface MobileVersionDao extends BaseDao<MobileVersion> {
    MobileVersion queryObject(Long versionId);

    List<MobileVersion> queryList(Map<String, Object> map);

    List<MobileVersion> queryListParentID(Map<String, Object> map);

    List<MobileVersion> queryReamrkByVersionId(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void deleteBatch(String[] versionIds);

}
