package cn.gree.zz.service;

import cn.gree.zz.base.DaoSupport;
import cn.gree.zz.domain.User;

public interface UserService extends DaoSupport<User> {
	/**
	 * 
	 * @param loginName
	 * @param password 使用md5加密的密码
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
