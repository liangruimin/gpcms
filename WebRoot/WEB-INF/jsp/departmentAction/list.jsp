<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>部门列表</title>
	<%@ include file="/WEB-INF/jsp/public/public.jspf" %>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />

</head>

<body>
<!-- 页面标题 -->
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">部门管理</a></li>
    </ul>
</div>

<div class="rightinfo">
    <table  class="tablelist">     
        <!-- 表头-->
        <thead>
            <tr>
            	<th>部门名称</th>
				<th>上级部门</th>
				<th>职能说明</th>
				<th>相关操作</th>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody>
        
        <s:iterator value="departmentList">
			<tr>
				<td>
					<s:a action="department_list?parentId=%{id}">${name}</s:a>
				</td>
				<td>${parent.name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a cssClass="tablelink" action="department_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
					<s:a cssClass="tablelink" action="department_editUI?id=%{id}">修改</s:a>
				</td>
			</tr>
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div class="tools" style="margin-top: 10px;">
    	<ul class="toolbar">
	        <li><s:a action="department_addUI?parentId=%{#parent.id}"><span><img src="${pageContext.request.contextPath}/images/t01.png" /></span>添加</s:a></li>
	       	
	       	<%--当不是顶级部门列表页面时，才需要显示上一级的按钮 --%>
	       	<s:if test="#parent != null">
	       		<li><s:a action="department_list?parentId=%{#parent.parent.id}"><span></span>返回上一级</s:a></li>
	       	</s:if>
       	</ul>    	
    </div>
    
	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
