package cn.gree.zz.service;

import java.util.List;

import cn.gree.zz.base.DaoSupport;
import cn.gree.zz.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {
	/**
	 * 查询所有上级权限的列表
	 * @return
	 */
	List<Privilege> findTopList();
	
	/**
	 * 查询出所有的权限的URL集合（不包含null值和重复的值）
	 * @return
	 */
	List<String> getAllPrivilegeUrls();

}
