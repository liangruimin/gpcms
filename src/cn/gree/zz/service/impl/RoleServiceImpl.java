package cn.gree.zz.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gree.zz.base.DaoSupportImpl;
import cn.gree.zz.domain.Role;
import cn.gree.zz.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {

	@Override
	public Role getByName(String name) {
		return (Role)getSession().createQuery(//
				"FROM Role r WHERE r.name = ?")//
				.setParameter(0, name)//
				.uniqueResult();
	}	

}
