package com.kylin.modules.system.service;


import com.kylin.modules.system.entity.Mobile;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * 
 * @author zhaohongbo
 * @date 2017-12-18 15:22:06
 */
public interface MobileService {

	Mobile queryObject(String mobileNum);
	
	List<Mobile> queryList(Map<String, Object> map);

	List<Mobile> queryVersionNo(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);

	int queryMobileNum(String inquireMobileNum);
	
	void save(String mobileNum, String stationNum) throws Exception;
	
	void update(Mobile mobile) throws Exception;
	
	void delete(String mobileNum);
	
	void deleteBatch(String[] mobileNums);

	Integer createStationTable(String stationNum) throws Exception;

	void createStationView() throws Exception;

	Mobile querySyncState(String mobileNum, String stationNum);

	void updateSyncState(Map<String, Object> map);
	/**
	 * 查询版本号
	 * @param mobileNum
	 * @param stationNum
	 * @return
	 */
	Mobile queryVersion(String mobileNum, String stationNum);
	/**
	 * 获取app安装包路径
	 * @param versionNo
	 * @param deptNum
	 * @return
	 */
	String getAppRoute(String versionNo, String deptNum);
}
