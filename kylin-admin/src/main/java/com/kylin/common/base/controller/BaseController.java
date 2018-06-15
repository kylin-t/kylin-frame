package com.kylin.common.base.controller;

import com.kylin.modules.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Description: Controller公共组件
 * @author: kylin
 * @create: 2018-01-30 10:06
 **/
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SysUser getUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}

	protected String getUsername(){
		return getUser().getUsername();
	}

	protected Long getDeptId() {
		return getUser().getDeptId();
	}

}
