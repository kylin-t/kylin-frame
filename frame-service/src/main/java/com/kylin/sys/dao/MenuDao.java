package com.kylin.sys.dao;

import com.kylin.sys.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:  菜单管理
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/04/29 15:52
 */
@Mapper
public interface MenuDao extends BaseDao<Menu> {
    /**
     * 根据父菜单id查询所有的子菜单列表
     * @param parentId
     * @return
     */
    List<Menu> queryListByParentId(Long parentId);
}
