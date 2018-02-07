package cn.gree.zz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gree.zz.base.DaoSupportImpl;
import cn.gree.zz.bean.Plan;
import cn.gree.zz.bean.Task;
import cn.gree.zz.domain.Department;
import cn.gree.zz.domain.Model;
import cn.gree.zz.domain.User;
import cn.gree.zz.service.DepartmentService;
import cn.gree.zz.service.ModelService;
import cn.gree.zz.service.PlanService;
import cn.gree.zz.service.TaskService;
import cn.gree.zz.service.UserService;

@Service
@Transactional
public class TaskServiceImpl extends DaoSupportImpl<Task> implements TaskService {
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected ModelService modelService;
	@Resource
	protected PlanService planService;
	
	@Override
	public List<Task> getTaskListByDynamic(User user,String checkTime) {
		//将查询条件封装到map中
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("checkTime", checkTime);
		//得到查询结果
		List<Task> taskList = queryResultList(Task.class,map);
		return taskList;
	}

	@Override
	public Task saveTask(String depId, String code, String result, String userNo) {
		Department department = departmentService.getById(1L);
		Model model = modelService.getById(1l);
		Plan plan = planService.getById(1L);
		User user = userService.findByloginName(userNo);
		
		//关联ERP信息 订单 机型 线体 班组		
		//基础信息 状态
		String status = "";
		if(result == "1"){  //合格
			status = "已完成" ;
		}else{
			status = "待处理" ;
		}
		//封装实体类
		Task task = new Task();
		task.setId(1L);
		task.setOrderNo("ZK0123456");
		task.setCode("211DSD654855B565");
		task.setResult(1);
		task.setUser(user);
		task.setCheckTime(new Date());
		task.setStatus(status);
		task.setModel(model);
		task.setDepartment(department);
		task.setLine("1N5");
		task.setTeam("zzz");
		task.setPlan(plan);
		
		return task;
	}

}
