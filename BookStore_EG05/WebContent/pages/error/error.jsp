<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误页面</title>
<!-- 引入base标签和css样式+jquery文件  include一定要使用绝对路径 -->
<%@ include file="/WEB-INF/include/base.jsp" %>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">错误页面</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<h2 style="color: red;text-align: center;">服务器走丢了，请联系管理员解决...</h2>
	</div>
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>