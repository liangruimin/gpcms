package cn.gree.zz.service;

import cn.gree.zz.base.DaoSupport;
import cn.gree.zz.domain.Role;

public interface RoleService extends DaoSupport<Role>{
	/**
	 * 根据name查找岗位
	 * @param string
	 * @return
	 */
	Role getByName(String string);

}
