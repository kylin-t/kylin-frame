package com.kylin.modules.system.service.impl;

import com.kylin.core.annotation.DataFilter;
import com.kylin.modules.system.dao.SysDeptDao;
import com.kylin.modules.system.entity.SysDept;
import com.kylin.modules.system.service.SysDeptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl  implements SysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;

	@Override
	public SysDept queryObject(Long deptId) {
		return sysDeptDao.queryObject(deptId);
	}
	@Override
	@DataFilter(tableAlias = "d",user = false)
	public List<SysDept> queryList(Map<String, Object> map) {
		return sysDeptDao.queryList(map);
	}
	@Override
	public List<Long> queryDetpIdList(Long parentId) {
		return sysDeptDao.queryDetpIdList(parentId);
	}

	@Override
	public void delete(Long deptId) {
		sysDeptDao.delete(deptId);
	}

	@Override
	public void update(SysDept sysDept) {
		sysDeptDao.update(sysDept);
	}

	@Override
	public void save(SysDept sysDept) {
		sysDeptDao.save(sysDept);
	}

	@Override
	public String getSubDeptIdList(Long deptId){
		//部门及子部门ID列表
		List<Long> deptIdList = new ArrayList<>();

		//获取子部门ID
		List<Long> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		//添加本部门
		deptIdList.add(deptId);

		String deptFilter = StringUtils.join(deptIdList, ",");
		return deptFilter;
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<Long> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}

	@Override
	public List<SysDept> getChildDepts(Long deptId) {
		return sysDeptDao.getChildDepts(deptId);
	}

	@Override
	public String getChildDeptIds(Long deptId) {
		List<Long> childDeptIds = sysDeptDao.getChildDeptIds(deptId);
		String deptFilter = StringUtils.join(childDeptIds, ",");
		return deptFilter;
	}
}
