package cn.gree.zz.bean;

import java.util.Date;

import cn.gree.zz.domain.Department;
import cn.gree.zz.domain.Model;
import cn.gree.zz.domain.User;

public class Task {
	private Long id;
	private String orderNo;  //订单
	private String code;     //抽检成品码
	private int result;  //抽检结果
	private User user;  //抽检人
	private Date checkTime;  //抽检时间
	private String status;   //任务状态
	private Model model;     //机型信息
	private Department department;  //分厂
	private String line;     //线体
	private String team;     //班组
	private Plan plan;    //所属抽检计划
	
	//Getter And Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

}
