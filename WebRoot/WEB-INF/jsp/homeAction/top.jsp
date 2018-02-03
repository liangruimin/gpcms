<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Top</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){	
			//顶部导航切换
			$(".nav li a").click(function(){
				$(".nav li a.selected").removeClass("selected")
				$(this).addClass("selected");
			});
		});
	</script>

</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    	<a href="#" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>
    
    <ul class="nav">
    <li><a href="#" ><img src="images/icon01.png" title="工作台" /><h2>数据统计</h2></a></li>
    <li><a href="#" class="selected"><img src="images/icon02.png" title="模型管理" /><h2>数据统计</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    	<li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    	<li><a href="#">关于</a></li>
    	<li><s:a action="loginout_logout" target="_parent">退出</s:a></li>
    </ul>
     
    <div class="user">
    <span>欢迎您：${user.name}</span>
    </div>    
    
    </div>

</body>
</html>
