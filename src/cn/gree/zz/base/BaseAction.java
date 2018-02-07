package cn.gree.zz.base;

import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;

import cn.gree.zz.domain.Role;
import cn.gree.zz.domain.User;
import cn.gree.zz.service.DepartmentService;
import cn.gree.zz.service.ModelService;
import cn.gree.zz.service.PlanService;
import cn.gree.zz.service.PrivilegeService;
import cn.gree.zz.service.RoleService;
import cn.gree.zz.service.TaskService;
import cn.gree.zz.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	// ===================== 声明Service ====================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected TaskService taskService;
	@Resource
	protected ModelService modelService;
	@Resource
	protected PlanService planService;

	// ===================== 对ModelDriven的支持 ====================

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取T的真是类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			@SuppressWarnings("unchecked")
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}
	
	// ========================== 工具方法 ==========================
	/**
	 * 获取当前登录的用户
	 */
	public User getCurrentUser() {
		return (User)ActionContext.getContext().getSession().get("user");
	}
	/**
	 * 判断当前登录的用户是不是管理员
	 */
	public boolean isManger() {
		User user = getCurrentUser();
		if ("admin".equals(user.getLoginName())) {
			return true;
		} else {
			Set<Role> roles = user.getRoles();
			Role role = roleService.getByName("管理员");
			if (roles.contains(role) == true) {
				return true;
			} else {
				return false;
			}
		}
	 	
	}
	/**
	 * 获取当前时间
	 */
	public String getCurrentTime() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	/**
	 * 检查传过来的字段是否合格
	 * @return
	 */
	public boolean checkType(String depId,String code,String result,String userNo){
		Map<String,String> map = new HashMap<String,String>();
		map.put("depId", depId);
		map.put("code", code);
		map.put("result", result);
		map.put("userNo", userNo);
		boolean flag = true;
		for(Entry<String,String> entry:map.entrySet()){
			if(entry.getValue().equals("") || entry.getValue()==null){
				flag = false;
				break;
			}
		}
		return flag;
	}

}
