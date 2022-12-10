<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<!-- 引入base页面 
	jsp中的所有的绝对路径和相对路径都是由服务器解析，和base标签没有关系
	jsp标签或include指令中的路径一定使用绝对路径
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	/*
		希望用户在数量的input的表单项中输入了数量并且改变了标签的焦点后提交修改请求给CartServlet.updateCount方法处理
		input表单项有内容改变的事件：onchange
			- 如果表单项内的值发生改变并且失去了焦点 会触发表单项的onchange事件
	
	*/
	/* 给删除超链接绑定单机时间，点击时给用户提示 */
	$(function(){
	$(".countInp").change(function(){
		//1、获取修改的数量+要修改的图书的id
		var count = this.value;
		var bookId = this.id;//触发内容改变监听的表单项的id值就是所在行的图书的id
		//2、提交修改请求给服务器
		window.location = "${pageContext.request.contextPath}/CartServlet?type=updateCount&count="
				+count+"&bookId="+bookId;
	});
		
		$(".delA").click(function(){
			var title = $(this).parents("tr").children("td:first").text();
			var flag = confirm("你真的要删除《"+title+"》吗？");
			if(!flag){
				//取消删除，取消a标签的默认行为
				return false;
			}
			
		});
	});
	
</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<!-- 判断 session中的购物车对象是否有购物项数据 -->
		<c:choose>
			<c:when test="${empty sessionScope.cart.cartItemList }">
				<h3>购物车中空空如也，赶紧去添加吧!!!</h3>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>		
					<c:forEach items="${sessionScope.cart.cartItemList }" var="cartItem">
						
						<tr>
							
							<td>${cartItem.book.title }</td>
							<td><input id="${cartItem.book.id }" type="text" class="countInp" value="${cartItem.count }" style="width: 30px;text-align: center;"/></td>
							<td>${cartItem.book.price }</td>
							<td>${cartItem.amount }</td>
							<td><a class="delA" href="CartServlet?type=deleteCartItem&bookId=${cartItem.book.id }">删除</a></td>
						</tr>	
					</c:forEach>
				</table>
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount }</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${cart.totalAmount }</span>元</span>
					<span class="cart_span"><a href="CartServlet?type=clearCart">清空购物车</a></span>
					<span class="cart_span"><a href="OrderClientServlet?type=checkOut">去结账</a></span>
				</div>
			</c:otherwise>
		</c:choose>

	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>