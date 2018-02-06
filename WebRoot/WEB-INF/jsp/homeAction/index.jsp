<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>抽测</title>
		<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<frameset rows="88,*,31" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="home_top.do" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
			<frame src="home_left.do" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="home_right.do" name="rightFrame" id="rightFrame" title="rightFrame" />
		</frameset>
		<frame src="home_bottom.do" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" />
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>

