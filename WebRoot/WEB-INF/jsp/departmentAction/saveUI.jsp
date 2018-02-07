<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>部门设置</title>
	<%@ include file="/WEB-INF/jsp/public/public.jspf" %>
	<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath }/css/selectB.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/select-ui.min.js"></script>
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
	<div class="formtitle"><span>部门信息</span></div>

    <s:form action="department_%{id == null ? 'add' : 'edit'}">
		<s:hidden name="id"></s:hidden>
        
        <!-- 表单内容显示 -->
    	<ul class="forminfo">
    		<li><label>上级部门</label>
				<div class="vocation">
			    	<s:select name="parentId" cssClass="select1"
                     		list="departmentList" listKey="id" listValue="name"
                     		headerKey="" headerValue="==请选择部门=="
                     >
                     </s:select>
			    </div>
			</li>
			<li><label>部门名称</label><s:textfield name="name" type="text" cssClass="dfinput" ></s:textfield><i>*</i></li>
    		<li><label>职能说明</label><s:textarea name="description" cssClass="beizhuinput"></s:textarea></li>
			<li style="padding-top: 10px;"><label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="保存"/>
		    	<input name="" type="button" class="btn" value="返回" onclick="javascript:history.go(-1);"/>
		    </li>
		</ul>
			
    </s:form>
</div>

<div style="padding-left:18px;">
	说明：<br />
	1，上级部门的列表是有层次结构的（树形）。<br/>
	2，如果是修改：上级部门列表中不能显示当前修改的部门及其子孙部门。因为不能选择自已或自已的子部门作为上级部门。<br />
</div>

</body>
</html>
