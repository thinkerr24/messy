<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	<!-- 将用户访问首页的请求转发给Servlet查询分页的方法处理 -->
	<jsp:forward page="/BookClientServlet?type=getPage&pageNumber=1"></jsp:forward>
	
</body>
</html>