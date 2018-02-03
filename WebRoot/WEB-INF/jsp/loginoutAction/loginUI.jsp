<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>GreeOA</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/cloud.js" type="text/javascript"></script>
	<!-- 云特效 -->
	<script language="javascript">
		$(function(){
			$(".loginbox").css({"position":"absolute","left":($(window).width()-692)/2});
			$(window).resize(function(){  
			    $(".loginbox").css({"position":"absolute","left":($(window).width()-692)/2});
		    }); 
		});  
	</script>
	<!-- 防止登录嵌套 -->
	<script type="text/javascript">
		if( window.parent != window ){
			window.parent.location.href = window.location.href;
		}
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
	    <li><a href="#">回首页</a></li>
	    <li><a href="#">帮助</a></li>
	    <li><a href="#">关于</a></li>
	    </ul>    
    </div>
    
    <s:form action="loginout_login">
	    <div class="loginbody">   
		    <span class="systemlogo"></span>       
		    <div class="loginbox">		    			    	
			    <ul>
			    <li>
			    	<div style="color:red"><s:fielderror></s:fielderror></div>
			    </li>
			    <li><s:textfield name="loginName" cssClass="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>	    
			    <li><s:password name="password" cssClass="loginpwd" showPassword="false" value="密码" onclick="JavaScript:this.value=''"/></li>
			    <li><input name="" type="submit" class="loginbtn" value="登录" " /></li>
			    </ul>   
		    </div>   
	    </div>
    </s:form>
       
    <div class="loginbm">版权所有@ 2017  <a href="#">格力电器（郑州）有限公司.信息网络科</a> </div>
	    
</body>

</html>

	
