package com.kylin.modules.system.service.impl;

import com.kylin.common.exception.RRException;
import com.kylin.modules.system.dao.DictDataDao;
import com.kylin.modules.system.dao.DictTypeDao;
import com.kylin.modules.system.entity.DictData;
import com.kylin.modules.system.entity.DictType;
import com.kylin.modules.system.service.DictTypeService;
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
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeDao dictTypeDao;
    @Autowired
    private DictDataDao dictDataDao;

    @Override
    public List<DictType> queryList(Map<String, Object> map) {
        return dictTypeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dictTypeDao.queryTotal(map);
    }

    @Override
    public void save(DictType dictType) {
        dictTypeDao.save(dictType);
    }

    @Override
    public void update(DictType dictType) {
        dictTypeDao.update(dictType);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        dictTypeDao.deleteBatch(ids);
    }

    @Override
    public void delete(Long id) {
        DictType dictType = dictTypeDao.queryObject(id);
        String typeKey = dictType.getTypeKey();
        DictData dictData = dictDataDao.queryByKey(typeKey);

        if (dictData != null){
            throw new RRException("该目录下包含数据,无法删除");
        }

        dictTypeDao.delete(id);
    }

    @Override
    public DictType queryObject(Long id) {
        return dictTypeDao.queryObject(id);
    }
}
