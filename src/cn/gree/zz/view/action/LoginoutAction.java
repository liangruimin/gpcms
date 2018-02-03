package cn.gree.zz.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.gree.zz.base.BaseAction;
import cn.gree.zz.domain.User;

@Controller
@Scope("prototype")
public class LoginoutAction extends BaseAction<User> {
	
	/*登录页面*/
	public String loginUI() {	
		return "loginUI";
	}
	/*登录*/
	public String login() {
		//验证用户名密码，如果正确就返回这个用户,否则返回null
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		
		// 如果登录名或密码不正确，就转回到登录页面并提示错误消息
		if (user == null) {
			addFieldError("login","登录名或密码不正确");
			return "loginUI";
		} else {
			ActionContext.getContext().getSession().put("user", user);
			return "toHome";
		}			
	}
	/*注销*/
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
}
