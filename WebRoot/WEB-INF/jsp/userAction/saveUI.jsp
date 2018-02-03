<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户信息</title>
    <%@ include file="/WEB-INF/jsp/public/public.jspf" %>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/selectB.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
	<script type="text/javascript">
		$("document").ready(function(e) {
		    $(".select1").uedSelect({
				width : 345			  
			});
		});
	</script>
</head>
<body>

<!-- 标题显示 -->
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">用户信息</a></li>
    </ul>
</div>

<!--显示表单内容-->
<div class="formbody">
    <div class="formtitle"><span>用户信息</span></div>
    
    <s:form action="user_%{id == null ? 'add' : 'edit'}">
        <s:hidden name="id"></s:hidden>
        
        <!-- 表单内容显示 -->
    	<ul class="forminfo">
                    <li><label>所属部门</label>
                    	<div class="vocation">
                        	<s:select name="departmentId" cssClass="select1"
                        		list="departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>
                        </div>
                    </li>
                    <li><label>登录名</label>
                        <s:textfield name="loginName" cssClass="dfinput"/> *
							（登录名要唯一）
                    </li>
                    <li><label>姓名</label>
                        <s:textfield name="name" cssClass="dfinput"/> *
                    </li>
					<li><label>性别</label>
                        <cite>
                        <input name="gender" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="gender" type="radio" value="女" />女</cite>
                    </li>
					<li><label>联系电话</label>
                        <s:textfield name="phoneNumber" cssClass="dfinput"/>
                    </li>
                    <li><label>邮箱</label>
                        <s:textfield name="email" cssClass="dfinput"/>
                    </li>
                    <li><label>职级</label>
                        <div class="vocation">
						    <select name="rank" class="select1">
							    <option value="总经理">总经理</option>
							    <option value="部长">部长</option>
							    <option value="副部长">副部长</option>
							    <option value="部长助理">部长助理</option>
							    <option value="科长">科长</option>
							    <option value="员工">员工</option>
						    </select>
					    </div>
                    </li>
                    <li><label>备注</label>
                        <s:textarea name="description" cssClass="beizhuinput"></s:textarea>
                    </li>

		</ul>     
		   
        <div class="formtitle"><span>岗位信息</span></div>
        
        <!-- 表单内容显示 -->
	    <ul class="forminfo">
			<li><label>岗位</label>
               	<s:select name="roleIds" multiple="true" size="7" 
               		list="roleList" listKey="id" listValue="name" style="width: 250px; border:#a7b5bc solid 1px;"
               	/>按住Ctrl键可以多选或取消选择                      
	        </li>
	    
		
	        <!-- 表单操作 -->
	        <li style="padding-top: 10px;"><label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="保存"/>
		    	<input name="" type="button" class="btn" value="返回" onclick="javascript:history.go(-1);"/>
		    </li>
	    </ul>
    </s:form>
</div>

</body>
</html>

