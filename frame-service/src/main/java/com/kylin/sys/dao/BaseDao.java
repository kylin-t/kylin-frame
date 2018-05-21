package com.kylin.sys.dao;

import java.util.List;
import java.util.Map;

/**
 * @Description:  基础DAO
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 14:52
 */
public interface BaseDao<T> {
	
	int save(T t);
	
	int save(Map<String, Object> map);
	
	int saveBatch(List<T> list);
	
	int update(T t);
	
	int update(Map<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] ids);

	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);

	List<T> queryListByBean(T t);

	List<T> queryList(Object id);

	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
