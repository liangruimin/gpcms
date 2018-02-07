package cn.gree.zz.view.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;

import cn.gree.zz.base.BaseAction;
import cn.gree.zz.bean.Task;
import cn.gree.zz.domain.User;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TaskAction extends BaseAction<Task> {
	/**
	 * 列表
	 * @param user
	 * @param checkTime 抽检时间默认为当月所有的
	 * @return
	 */
	public String list(){
		//1.获取查询条件
		User user = getCurrentUser();
		String checkTime = "2018-02";
		
		//2.动态查询任务列表
		List<Task> taskList = null;
		taskList = taskService.getTaskListByDynamic(user,checkTime);
		//3.数据放入值栈
		ActionContext.getContext().put("taskList", taskList);
		return "list";
	}
	/**
	 * 手机端添加
	 * @param /分厂/成品码/抽检结果/抽检人
	 * @return
	 */
	public String add(){
		//1.获取查询条件
		HttpServletRequest request = ServletActionContext.getRequest();
		String depId = request.getParameter("depId");  //分厂
		String code = request.getParameter("code");    //成品码
		String result = request.getParameter("result");//结论
		String userNo = request.getParameter("userNo");//抽检人
		//创建一个Json,留作输出
		JsonObject object = new JsonObject();
		
		//把数据插入Task中
		if(checkType(depId,code,result,userNo)){   //检查参数是否合乎规范
			//关联信息
			Task task = taskService.saveTask(depId,code,result,userNo);
			taskService.save(task);
			
			object.addProperty("res", "success");
			object.addProperty("msg", "任务成功");
		}else{
			object.addProperty("res", "fail");
			object.addProperty("msg", "由于XXX原因导致失败");
		}
		
		//把json处理成字符串
		String isResult = object.toString();
		//System.out.println(isResult);
		//数据发送到前台
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		
		try { 
		     response.getWriter().write(isResult); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		
		return null;
	}

}
