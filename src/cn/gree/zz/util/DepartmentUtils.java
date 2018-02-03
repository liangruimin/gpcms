package cn.gree.zz.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.gree.zz.domain.Department;

public class DepartmentUtils {

	/**
	 * 遍历部门树，把所有的部门名称都改掉名称后放到同一个List中返回，通过名称中的空格显示层次
	 * @param removeDepartment 
	 */
	public static List<Department> getAllDepartmentList(List<Department> topList, Department removedDepartment){
		List<Department> list = new ArrayList<Department>();   //最后的结果放入list
		walkTree(topList,"┣",list , removedDepartment);
		return list;
	}
	
	/**
	 * 递归遍历
	 * @param list 
	 * @param string 
	 * @param topList 
	 */
	private static void walkTree(Collection<Department> topList, String prefix, List<Department> list ,Department removedDepartment){
		for (Department top:topList) {
			//去掉指定的部门分支
			if (removedDepartment != null && top.getId().equals(removedDepartment.getId())) {
				continue;
			}
			//顶点
			Department copy = new Department();
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy);
			//子树
			walkTree(top.getChildren(), "　" + prefix, list, removedDepartment);
		}
	}
}
