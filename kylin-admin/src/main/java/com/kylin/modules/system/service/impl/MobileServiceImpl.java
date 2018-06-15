package com.kylin.modules.system.service.impl;

import com.kylin.modules.system.dao.MobileDao;
import com.kylin.modules.system.entity.Mobile;
import com.kylin.modules.system.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("mobileService")
public class MobileServiceImpl implements MobileService {
	@Autowired
	private MobileDao mobileDao;

	@Override
	public Mobile queryObject(String mobileNum){
		return mobileDao.queryObject(mobileNum);
	}

	@Override
	public List<Mobile> queryList(Map<String, Object> map){
		return mobileDao.queryList(map);
	}

	@Override
	public List<Mobile> queryVersionNo(Map<String, Object> map) {
		return mobileDao.queryVersionNo(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return mobileDao.queryTotal(map);
	}

	@Override
	public int queryMobileNum(String inquireMobileNum) {
		return mobileDao.queryMobileNum(inquireMobileNum);
	}

	@Override
	public void save(String mobileNum, String stationNum) throws Exception {
		try {
			Mobile mobile = new Mobile();
			mobile.setMobileNum(mobileNum);
			mobile.setStationNum(stationNum);
			mobileDao.save(mobile);

			/* 绿通项目代码
			//创建收费站表及收费站表合并视图
			int result = createStationTable(stationNum);
			if (result >= 0) {
				if (result > 0) {
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("stationNum", stationNum);
					mobileDao.createInsertTrigger(paramMap);
					mobileDao.createUpdateTrigger(paramMap);
				}

				Mobile mobile = new Mobile();
                mobile.setMobileNum(mobileNum);
                mobile.setStationNum(stationNum);
                mobileDao.save(mobile);

				createStationView();
			}*/
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(Mobile mobile) throws Exception{
		Mobile old = queryObject(mobile.getMobileNum());
		String oldStationNum = old.getStationNum();
		try {
			mobileDao.update(mobile);
			/* 绿通项目建表、触发器、视图代码
			if (!oldStationNum.equals(mobile.getStationNum())) {
				//创建收费站表及收费站表合并视图
				int result = createStationTable(mobile.getStationNum());
				if (result >= 0) {
					if(result > 0) {
						Map<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("stationNum", mobile.getStationNum());
						mobileDao.createInsertTrigger(paramMap);
						mobileDao.createUpdateTrigger(paramMap);
					}
				    createStationView();
                }
			}*/
		} catch(Exception e) {
			throw e;
		}
		mobileDao.update(mobile);
	}

	@Override
	public void delete(String mobileNum){
		mobileDao.delete(mobileNum);
	}

	@Override
	public void deleteBatch(String[] mobileNums){
		mobileDao.deleteBatch(mobileNums);
	}

	@Override
	public Integer createStationTable(String stationNum) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stationNum", stationNum);
		map.put("result", -999);
		mobileDao.createStationTable(map);
		int result = Integer.valueOf(map.get("result").toString());
		String errText = "";
		if(result < 0) {
			switch (result) {
				case -1:
					errText = "收费站编码为空或无效，无法创建绿通数据表";
					break;
				case -2:
					errText = "收费站编码[" + stationNum + "]已经存在相关文件组或文件，无法创建";
					break;
				case -3:
					errText = "收费站编码[" + stationNum + "]创建文件组失败";
					break;
				case -4:
					errText = "收费站编码[" + stationNum + "]创建文件失败";
					break;
				case -5:
					errText = "收费站编码[" + stationNum + "]创建绿通数据表失败";
					break;
				default:
					errText = "收费站编码[" + stationNum + "]创建绿通数据表发生意外错误";
			}
			throw  new RuntimeException(errText);
		}

		return result;
	}

	@Override
	public void createStationView()  throws Exception{
		Map<String, Integer> resultMap = new HashMap<String,Integer>();
		resultMap.put("result", Integer.valueOf (-999));
		mobileDao.createStationView(resultMap);
		if (resultMap.get("result").intValue() < 0){
			throw  new RuntimeException("创建绿通数据视图失败");
		}
	}

	@Override
	public Mobile querySyncState(String mobileNum, String stationNum) {
		return mobileDao.querySyncState(mobileNum,stationNum);
	}

	@Override
	public void updateSyncState(Map<String, Object> map) {
		mobileDao.updateSyncState(map);
	}

	@Override
	public Mobile queryVersion(String mobileNum, String stationNum) {
		return mobileDao.queryVersion(mobileNum,stationNum);
	}

	@Override
	public String getAppRoute(String versionNo, String deptNum) {
		String appRoute = mobileDao.getAppRoute(new BigDecimal(versionNo),deptNum);
		return appRoute;
	}
}
