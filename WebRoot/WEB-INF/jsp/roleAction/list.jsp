<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
    <title>岗位列表</title>
	<%@ include file="/WEB-INF/jsp/public/public.jspf" %>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">岗位管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    	
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>岗位名称</th>
        <th>岗位说明</th>
        <th>相关操作</th>
        </tr>
        </thead>
        
        <tbody datakey="roleList">
	        <s:iterator value="roleList">
		        <tr>
		        <td>${name}&nbsp;</td>
		        <td>${description}&nbsp;</td>
		        <td><s:a action="role_editUI?id=%{id}">修改</s:a>
		        	<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
		        	<s:a action="role_setPrivilegeUI?id=%{id}">设置权限</s:a>
		        </td>
		        </tr>
	        </s:iterator>  
        </tbody>
    </table>
    
    <div class="tools" style="margin-top: 10px;">    
    	<ul class="toolbar">
        	<li><s:a action="role_addUI"><span><img src="${pageContext.request.contextPath}/images/t01.png" /></span>添加</s:a></li>
        </ul>
    </div>
    
    </div>
    
    <script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>
</html>
