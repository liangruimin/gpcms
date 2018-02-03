package cn.gree.zz.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class TestAction extends ActionSupport{
	@Resource
	private TestService testService;
	
	public String execute(){
		System.out.println("TestAction.execute()");
		System.out.println("testService----" +testService);//获取到testService说明testService被注入到Spring中去了，同时说明这个Testaction对象交个Spring管理了
		testService.saveTwoUsers();
		return "success";
	}
}
