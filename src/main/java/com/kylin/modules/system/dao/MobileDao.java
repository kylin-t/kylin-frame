package com.kylin.modules.system.dao;

import com.kylin.common.base.dao.BaseDao;
import com.kylin.modules.system.entity.Mobile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * 
 * @author zhaohongbo
 * @date 2017-12-18 15:22:06
 */
@Mapper
public interface MobileDao extends BaseDao<Mobile> {
    void createStationTable(Map<String, Object> m) throws Exception;

    void createStationView(Map<String, Integer> resultMap) throws Exception;

    void createInsertTrigger(Map<String, String> paramMap) throws Exception;
    void createUpdateTrigger(Map<String, String> paramMap) throws Exception;

    int queryMobileNum(String queryMobileNum);

    List<Mobile> queryVersionNo(Map<String, Object> map);

    /**
     * 查询数据同步状态
     * @param mobileNum
     * @param stationNum
     * @return
     */
    Mobile querySyncState(@Param("mobileNum") String mobileNum, @Param("stationNum") String stationNum);

    /**
     * 更新数据同步状态
     * @param map
     */
    void updateSyncState(Map<String, Object> map);

    /**
     * 查询版本号
     * @param mobileNum
     * @param stationNum
     * @return
     */
    Mobile queryVersion(@Param("mobileNum") String mobileNum, @Param("stationNum") String stationNum);

    /**
     * 获取app安装包路径
     * @param versionNo
     * @param deptNum
     * @return
     */
    String getAppRoute(@Param("versionNo") BigDecimal versionNo, @Param("deptNum") String deptNum);
}
