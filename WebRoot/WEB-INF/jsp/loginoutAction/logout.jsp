<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>您已退出Gree OA系统</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/cloud.js" type="text/javascript"></script>
	<script language="javascript">
		$(function(){
			$(".loginbox").css({"position":"absolute","left":($(window).width()-692)/2});
			$(window).resize(function(){  
			    $(".loginbox").css({"position":"absolute","left":($(window).width()-692)/2});
		    }); 
		});  
	</script> 
	
</head>
<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
	    <div id="mainBody">
	      <div id="cloud1" class="cloud"></div>
	      <div id="cloud2" class="cloud"></div>
	    </div>  
	
	
		<div class="logintop">    
		    <span>欢迎登录物流自动办公平台</span>    
		    <ul>
		    <li><a href="#">帮助</a></li>
		    <li><a href="#">关于</a></li>
		    </ul>    
	    </div>
	    
		    <div class="loginbody">   
			    <span class="systemlogo"></span>       
			    <div class="loginbox">		    			    	
				    <ul>
				    	<li><label><a href="${pageContext.request.contextPath}/loginout_loginUI.do"> >>>重新进入系统</a></label>
				    	<label><a href="javascript:window.open('', '_self'); window.close();"> >>>关闭当前窗口</a></label></li>
				    </ul>   
			    </div>   
		    </div>
	    
	    
	    <div class="loginbm">版权所有@ 2017  <a href="#">格力电器（郑州）有限公司.信息网络科</a> </div>
			    
	</body>
</html>
	