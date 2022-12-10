<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <c:choose>
   	<c:when test="${empty sessionScope.user }">
   		 <!-- 用户未登录显示的头部 
	 	登录成功和未登录的头部只能显示一个在页面中
			 -->
			 <!-- 所有的相对路径都参考base标签： 到项目名 -->
		 <div>
			<a href="pages/user/login.jsp">登录</a> | 
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="pages/manager/manager.jsp">后台管理</a>
			<a href="index.jsp">返回</a>
		</div>
   	</c:when>
  		<c:otherwise>
	    <!-- 用户登录成功显示的头部 -->
  			<div>
			<span>欢迎<span class="um_span">${user.username }</span>光临尚硅谷书城</span>
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="OrderClientServlet?type=getOrders">我的订单</a>
			<a href="UserServlet?type=logout">注销</a>&nbsp;&nbsp;
			<a href="index.jsp">返回</a>
		</div>
  		</c:otherwise>
  </c:choose>
	