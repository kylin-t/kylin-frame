package com.kylin.modules.system.service.impl;

import com.kylin.modules.system.dao.MobileVersionDao;
import com.kylin.modules.system.entity.MobileVersion;
import com.kylin.modules.system.service.MobileVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author hy
 * @Date 2018/1/11 17:46
 */
@Service("MobileVersionService")
public class MobileVersionServiceImpl implements MobileVersionService {

    @Autowired
    private MobileVersionDao mobileVersionDao;

    @Override
    public MobileVersion queryObject(Long versionId) {
        return mobileVersionDao.queryObject(versionId);
    }

    @Override
    public List<MobileVersion> queryList(Map<String, Object> map) {
        return mobileVersionDao.queryList(map);
    }

    @Override
    public List<MobileVersion> queryListParentID(Map<String, Object> map) {
        return mobileVersionDao.queryListParentID(map);
    }

    @Override
    public List<MobileVersion> queryReamrkByVersionId(Map<String, Object> map) {
        return mobileVersionDao.queryReamrkByVersionId(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return mobileVersionDao.queryTotal(map);
    }

    @Override
    public void save(MobileVersion mobileVersion) {
        mobileVersionDao.save(mobileVersion);
    }

    @Override
    public void update(MobileVersion mobileVersion) {
        mobileVersionDao.update(mobileVersion);
    }

    @Override
    public void deleteBatch(String[] versionIds) {
        mobileVersionDao.deleteBatch(versionIds);
    }
}
