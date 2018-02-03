package cn.gree.zz.test;

import javax.annotation.Resource;

import org.junit.Test;

import cn.gree.zz.domain.Role;
import cn.gree.zz.service.RoleService;

public class TestDao {
	//获取session
	@Resource
	private RoleService roleService;
	
	@Test
	public void testSavaDao(){
		
		Role role = new Role();
		role.setName("总裁");
		role.setDescription("管理公司");
		
		//保存到数据库
		roleService.save(role);
	}	
}
