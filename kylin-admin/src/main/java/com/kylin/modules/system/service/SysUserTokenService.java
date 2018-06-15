package com.kylin.modules.system.service;

import com.kylin.core.Result.Result;
import com.kylin.modules.system.entity.SysUserToken;

/**
 * @Description: 系统用户token
 * @author: kylin
 * @create: 2018-01-30 11:15
 **/
public interface SysUserTokenService {

	SysUserToken queryByUserId(Long userId);

	void save(SysUserToken token);
	
	void update(SysUserToken token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Result createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
