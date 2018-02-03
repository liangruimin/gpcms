<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>岗位设置</title>
	<%@ include file="/WEB-INF/jsp/public/public.jspf" %>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body> 

<!-- 标题显示 -->
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">添加岗位</a></li>
    </ul>
</div>

<!--显示表单内容-->
<div class="formbody">
    <div class="formtitle"><span>添加岗位</span></div>
    
    <s:form action="role_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>

        <!-- 表单内容显示 -->
        <ul class="forminfo">
		    <li><label>岗位名称</label><s:textfield name="name" type="text" cssClass="dfinput" ></s:textfield><i>必填</i></li>
		    
		    <li><label>岗位说明</label><s:textarea name="description" cols="" rows="" cssClass="beizhuinput"></s:textarea></li>
		    <!-- 表单操作 -->
		    <li><label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="保存"/>
		    	<input name="" type="button" class="btn" value="返回" onclick="javascript:history.go(-1);"/>
		    </li>
	    </ul>       
    </s:form>
</div>

</body>
</html>
