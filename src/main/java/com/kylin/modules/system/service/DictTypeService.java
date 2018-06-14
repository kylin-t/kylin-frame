package com.kylin.modules.system.service;

import com.kylin.modules.system.entity.DictType;

import java.util.List;
import java.util.Map;

/**
 * @Description: 字典分类
 * @author: kylin
 * @create: 2018-02-01 10:27
 **/
public interface DictTypeService {
    List<DictType> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DictType dictType);

    void update(DictType dictType);

    void deleteBatch(Long[] ids);

    void delete(Long id);

    DictType queryObject(Long id);
}
