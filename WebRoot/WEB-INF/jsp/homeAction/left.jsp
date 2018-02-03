<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
	<title>导航菜单</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<!-- 导航效果 -->
	<script type="text/javascript">
		$(function(){	
			//导航切换
			$(".title").click(function(){
				var $ul = $(this).next("ul");
				$("dd").find("ul").slideUp();
				if($ul.is(":visible")){
					$(this).next("ul").slideUp();
				}else{
					$(this).next("ul").slideDown();
				}
			});
		});	
	</script>
</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>功能导航</div>
	
	<dl class="leftmenu">
	
    	<s:iterator value="#application.topPrivilegeList">
			<s:if test=" #session.user.hasPrivilegeByName(name) ">   
			    <dd>     
		    	<div class="title">
		    		<span><img src="images/leftico01.png" /></span>${name}
		    	</div>
		    	<ul class="menuson">
		    		<s:iterator value="children">
		      			<s:if test=" #session.user.hasPrivilegeByName(name) ">
			        		<li><cite></cite>
			        			<a href="${pageContext.request.contextPath}${url}.do" target="rightFrame">${name}</a><i></i>
			        		</li>
			        	</s:if>
		         	</s:iterator>
		        </ul>
   				</dd> 
		   </s:if>	     
		</s:iterator> 
		
	</dl>
</body>
</html>
