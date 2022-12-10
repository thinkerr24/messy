<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">

h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty list }">
				<h3>你还没有订单，快去买吧！！！</h3>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>
					<c:forEach items="${list }" var="order">
						<tr>
							<td>${order.id }</td>
							<!-- 
								type: both 年月日时分秒/date 年月日/time 时分秒
								dateStyle: full/long/short
								timeStyle: full/long/short
							 -->
							<td><fmt:formatDate value="${order.orderTime }" type="both" dateStyle="short"  /></td>
							<td>${order.totalAmount }</td>
							<td>
								<c:choose>
									<c:when test="${order.state==0 }">未发货</c:when>
									<c:when test="${order.state==1 }">
										<a href="OrderClientServlet?type=takeGoods&orderId=${order.id }">确认收货</a>
									</c:when>
									<c:when test="${order.state==2 }">交易完成</c:when>
								</c:choose>
							</td>
							<td><a href="#">查看详情</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>

	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2018 </span>
	</div>
</body>
</html>