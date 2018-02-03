package cn.gree.zz.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.gree.zz.base.BaseAction;
import cn.gree.zz.domain.Department;
import cn.gree.zz.domain.Role;
import cn.gree.zz.domain.User;
import cn.gree.zz.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private Long departmentId;
	private Long [] roleIds;
	
	/**
	 * 列表
	 * @return
	 */
	public String list(){
		//准备数据，放入值栈
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		userService.delete(model.getId());
		return "toList";
	}
	/**
	 * 添加页面
	 * @return
	 */
	public String addUI(){
		//准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);
		//准备数据： roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		//封装对象
		// >> 处理关联的一个部门
		model.setDepartment(departmentService.getById(departmentId));	
		// >> 处理关联度额多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		
		//保存到数据库
		userService.save(model);
		return "toList";
	}
	/**
	 * 修改页面
	 * @return
	 */
	public String editUI(){
		//准备回显数据（从数据库中把这个数据拿出来,放入值栈的顶层）
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// >> 处理部门  user里部门保存的是一个Department，而我们要的是根据id部门名称
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		// >> 处理岗位 user里保存的岗位是一个Set<Role> 而我们要的是根据id数组岗位名称集合
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for (Role role:user.getRoles()) {
			roleIds[index++] = role.getId();
		}
		//准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);
		//准备数据： roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		
		return "saveUI";
	}
	/**
	 * 修改
	 * @return
	 */
	public String edit(){
		//1.从数据库中取出原对象
		User user = userService.getById(model.getId());
		
		//2.设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 处理关联的一个部门
		user.setDepartment(departmentService.getById(departmentId));
		// >>　处理关联的多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		
		//3.更新到数据库
		userService.update(user);
		return "toList";
	}
	/**
	 * 初始化密码为1234
	 * @return
	 */
	
	public String initPassword(){
		
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		// 密码要使用MD5摘要
		String md5 = DigestUtils.md5Hex("1234");
		user.setPassword(md5);

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
}
