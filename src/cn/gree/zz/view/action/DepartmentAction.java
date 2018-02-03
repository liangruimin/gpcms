package cn.gree.zz.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.gree.zz.base.BaseAction;
import cn.gree.zz.domain.Department;
import cn.gree.zz.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	
	private Long parentId;
	/**
	 * 列表:列表页面只显示一层（同级的）部门数据，默认显示最顶级的部门列表
	 * @return
	 */
	public String list(){
		List<Department> departmentList = null;

		if (parentId == null) {
			//默认显示顶级部门列表
			departmentList = departmentService.findTopList();
		} else {
			//显示顶级部门的子部门列表
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		//数据放入值栈
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		departmentService.delete(model.getId());
		return "toList";
	}
	/**
	 * 添加页面
	 * @return
	 */
	public String addUI(){
		//准备数据
		//List<Department> departmentList = departmentService.findAll();
		//ActionContext.getContext().put("departmentList", departmentList);
		
		//准备数据（树状结构）
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList,null);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		//封装数据
		Department parent= departmentService.getById(parentId);
		model.setParent(parent);
		//把数据存入数据库
		departmentService.save(model);
		return "toList";
	}
	/**
	 * 修改页面
	 * @return
	 */
	public String editUI(){
		//准备回显的数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {   //上级单位不等于空
			this.parentId = department.getParent().getId();
		}
		//准备数据
		//List<Department> departmentList = departmentService.findAll();
		
		//准备数据（树状结构）
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList,department);
		ActionContext.getContext().put("departmentList", departmentList);		
		return "saveUI";
	}
	
	public String edit(){
		//1. 从数据库中获取要修改的原始对象
		Department department = departmentService.getById(model.getId());
		//2. 设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		// >> 处理上级部门
		Department parent = departmentService.getById(parentId);
		department.setParent(parent);
		//3. 更新到数据库
		departmentService.update(department);
		return "toList";
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	

}
