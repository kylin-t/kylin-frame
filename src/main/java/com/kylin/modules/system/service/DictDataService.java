package com.kylin.modules.system.service;

import com.kylin.modules.system.entity.DictData;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: kylin
 * @create: 2018-02-01 10:27
 **/
public interface DictDataService {
    List<DictData> queryList(Map<String,Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DictData dictData);

    void update(DictData dictData);

    void deleteBatch(Long[] ids);

    DictData queryObject(Long id);
}
