package cn.gree.zz.base;

import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.Resource;

import cn.gree.zz.domain.Role;
import cn.gree.zz.domain.User;
import cn.gree.zz.service.DepartmentService;
import cn.gree.zz.service.PrivilegeService;
import cn.gree.zz.service.RoleService;
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

	// ===================== 对ModelDriven的支持 ====================

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取T的真是类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
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
	 * 获取当前月份
	 */
	public String getCurrentMonth() {
		DateFormat format = new SimpleDateFormat("yyyy年MM月");
		return format.format(new Date());
	}
	/**
	 * 获取当前季度
	 */
	public String getCurrentQuarter() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));  //获取东八区时间
		int year = calendar.get(Calendar.YEAR);      //获取年份
		int month = calendar.get(Calendar.MONTH)+1;  //获取月份，0表示1月份
		return year+"年"+month/3+"季度";  //当前季度
	}
	/**
	 * 获取当前年份
	 */
	public String getCurrentYear() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));  //获取东八区时间
		int year = calendar.get(Calendar.YEAR);      //获取年份
		int month = calendar.get(Calendar.MONTH)+1;  //获取月份，0表示1月份
		if (month == 6) {
			return year+"年半年度";
		} else {
			return year+"年年度";
		}		
	}

}
