<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Right</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b>您好！${user.name}，欢迎使用郑州格力中干KPI绩效管理系统</b>（信息网络科提供技术支持 版本: v2017.10.12 版）
    </div>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="images/time.png" alt="时间" /></span>
    <b>系统登录时间：</b>
    </div>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>信息管理系统使用指南</b>
    </div>
    
    <ul class="infolist">
    <li><span>您可以进行密码修改、账户设置等操作</span><a href="#" >前往...</a></li>
    <li><span>您可以管理和分配权限</span><a href="#" >前往...</a></li>
    <li><span>管理和设置部门</span><a href="#" >前往...</a></li>
    </ul>
    
    <div class="xline"></div>
    
    <div class="uimakerinfo"><b>系统发生异常请联系：信息网络科 （夏君）  总经办综合管理科 <a href="#" >（张慧杰）</a></b></div>
    
    <ul class="umlist">
    <li><a href="#">操作说明</a></li>
    </ul>
    
    
    </div>
</body>
</html>
 