<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <%@ include file="/WEB-INF/jsp/public/public.jspf" %>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.idTabs.min.js"></script>

</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">用户管理</a></li>
    <li><a href="#">人员管理</a></li>
    </ul>
</div>

<div class="rightinfo">
	
    <table class="tablelist">      
        <!-- 表头-->
        <thead>
            <tr>
		        <th>登录名</th>
		        <th>姓名</th>
		        <th>所属部门</th>
		        <th>岗位</th>
		        <th>职级</th>
		        <th>备注</th>
		        <th>相关操作</th>
	        </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody datakey="userList">
        
        <s:iterator value="userList">
            <tr>
                <td>${loginName}&nbsp;</td>
                <td>${name}&nbsp;</td>
                <td>${department.name}&nbsp;</td>
                <td>
                	<s:iterator value="roles">
                		${name}
                	</s:iterator>&nbsp;
                </td>
                <td>${rank}&nbsp;</td>
                <td>${description}&nbsp;</td>
                <td>
                	<s:a cssClass="tablelink" action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
                    <s:a cssClass="tablelink" action="user_editUI?id=%{id}">修改</s:a>
					<s:a cssClass="tablelink" action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
                </td>
            </tr>
        </s:iterator> 
            
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div class="tools" style="margin-top: 10px;">
    	<ul class="toolbar">
        	<li><s:a action="user_addUI"><span><img src="${pageContext.request.contextPath }/images/t01.png" /></span>添加</s:a></li>
    	</ul>
    </div>
    <script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</div>

</body>
</html>
