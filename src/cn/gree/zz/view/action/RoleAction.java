package cn.gree.zz.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.gree.zz.base.BaseAction;
import cn.gree.zz.domain.Privilege;
import cn.gree.zz.domain.Role;

import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private Long[] privilegeIds;
	/**
	 * 列表
	 * @return
	 */
	public String list(){
		//准备数据
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);//把数据放入值栈里
		return "list";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		roleService.delete(model.getId());
		return "toList";
	}
	/**
	 * 添加页面
	 * @return
	 */
	public String addUI(){
		return "saveUI";
	}
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		// 封装对象
		Role role = new Role();
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		System.out.println("-----------------"+role.getName());
		System.out.println("-----------------"+role.getDescription());
		//保存到数据库
		roleService.save(role);
		return "toList";
	}
	/**
	 * 修改页面
	 * @return
	 */
	public String editUI(){
		//准备回显的数据
		Role role = roleService.getById(model.getId());
		//结果放入栈顶
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}
	/**
	 * 修改
	 * @return
	 */
	public String edit(){
		//1. 从数据库中获取要修改的原始对象
		Role role = roleService.getById(model.getId());
		//2. 设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		//3. 更新到数据库
		roleService.update(role);
		
		return "toList";
	}
	//设置权限页面
	public String setPrivilegeUI(){
		//准备数据（所有权限列表）
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
		
		//准备要回显的数据（就是选中此岗位原本就有的权限）
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);  //放入栈顶
		
		//准备回显的权限数据
		privilegeIds = new Long[role.getPrivileges().size()];  //这个岗位的权限
		int index = 0;
		for (Privilege p:role.getPrivileges()) {
			privilegeIds[index++] = p.getId();  //获取这个role的所有权限的id集合
		}
		
		return "setPrivilegeUI";
	}
	
	//设置权限
	public String setPrivilege() {
		// 1.从数据库中获取要修改的原始role的权限
		Role role = roleService.getById(model.getId());
		
		// 2.设置要修改的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		
		// 3.更新到数据库
		roleService.update(role);
		
		return "toList";
	}
	
	//--
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
}
