<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<!-- <a href="pages/manager/book_manager.jsp">图书管理</a> -->
	<!-- 图书管理超链接点击时应该查询所有的图书  BaseServlet的子类被访问时必须提供type参数-->
	<a href="BookManagerServlet?type=getPage&pageNumber=1">图书管理</a>
	<a href="OrderManagerServlet?type=getAllOrders">订单管理</a>
	<a href="index.jsp">返回商城</a>
</div>