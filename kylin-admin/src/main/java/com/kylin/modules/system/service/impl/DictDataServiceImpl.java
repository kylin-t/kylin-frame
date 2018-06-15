package com.kylin.modules.system.service.impl;

import com.kylin.modules.system.dao.DictDataDao;
import com.kylin.modules.system.entity.DictData;
import com.kylin.modules.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 数据字典服务实现类
 * @author: kylin
 * @create: 2018-02-01 10:30
 **/
@Service
public class DictDataServiceImpl implements DictDataService {
    @Autowired
    private DictDataDao mDictDataDao;

    @Override
    public List<DictData> queryList(Map<String, Object> map) {
        return mDictDataDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return mDictDataDao.queryTotal(map);
    }

    @Override
    public void save(DictData dictData) {
        mDictDataDao.save(dictData);
    }

    @Override
    public void update(DictData dictData) {
        mDictDataDao.update(dictData);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        mDictDataDao.deleteBatch(ids);
    }

    @Override
    public DictData queryObject(Long id) {
        return mDictDataDao.queryObject(id);
    }
}
