package cn.gree.zz.service;

import java.util.List;

import cn.gree.zz.base.DaoSupport;
import cn.gree.zz.bean.Task;
import cn.gree.zz.domain.User;

public interface TaskService extends DaoSupport<Task> {
	
	/**
	 * 多条件动态组合查询任务列表
	 * @return
	 */
	List<Task> getTaskListByDynamic(User user,String checkTime);
	/**
	 * 封装Task
	 * @param depId
	 * @param code
	 * @param result
	 * @param userNo
	 * @return
	 */
	Task saveTask(String depId, String code, String result, String userNo);
	
}
