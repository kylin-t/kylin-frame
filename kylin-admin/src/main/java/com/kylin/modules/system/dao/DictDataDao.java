package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.DictData;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @author: kylin
 * @create: 2018-02-01 10:14
 **/
@Mapper
public interface DictDataDao extends BaseDao<DictData>{
    DictData queryByKey(String typeKey);
}
