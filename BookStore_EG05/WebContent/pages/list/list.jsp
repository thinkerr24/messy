<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$("#queryBtn").click(function(){
			var minPri = $("[name='min']").val();
			var maxPri = $("[name='max']").val();
			//检查价格区间和价格是否为正确的数字
			if(isNaN(minPri)||isNaN(maxPri)){
				alert("请输入正确的价格");
				return ;
			}else if((maxPri-minPri)<0){
				//最高价必须大于最低价
				alert("请输入正确的价格区间");
			}
			
			//向服务器发送按价格查询分页的请求
			window.location = "${pageContext.request.contextPath}/BookClientServlet?type=getPageByPrice&minPri="
					+minPri+"&maxPri="+maxPri+"&pageNumber="+1;
			
		});
		
		
		//异步的方式实现添加商品到购物车
		$(".addBK").click(function(){
			//获取到当前要添加到购物车的书的id
			var  bookId = this.id ;
			//发送异步请求
			$.ajax({
				url:"CartServlet",
				data:{"type":"addBook","bookId":bookId},
				type:"POST",
				dataType:"json",
				success:function(result){
					
					//alert(result.totalCount);
					//将数据更新到页面中.					
					$("#totalCountSpan").html("您的购物车中有 " + result.totalCount+ "件商品");
					$("#titleDiv").html("您刚刚将<span style='color: red'>"+result.title+"</span>加入到了购物车中");
				}
				
			});
			
			//取消超链接的默认行为
			return false ; 
			
		});
		
	});

</script>
</head>

<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				价格：<input type="text" name="min" value="${param.minPri }"> 元 - <input type="text" name="max"  value="${param.maxPri }"> 元 <button id="queryBtn">查询</button>
			</div>
			<div style="text-align: center">
				<c:choose>
					<c:when test="${empty cart.map }">
						<span>您的购物车中还没有商品，赶紧添加吧！！</span>
						<div>
							<br/>
						</div>
					</c:when>
					<c:otherwise>
						<span id="totalCountSpan">您的购物车中有${cart.totalCount }件商品</span>
						<div id="titleDiv">
							您刚刚将<span style="color: red">${sessionScope.title }</span>加入到了购物车中
						</div>
					</c:otherwise>
				</c:choose>
				
			</div>
			<c:choose>
				<c:when test="${empty page.data }">
					<!-- 分页集合中没有数据 -->
					<h3>你来晚了，图书卖光光了..............</h3>
				</c:when>
				<c:otherwise>
					<!-- 遍历集合中的数据显示，每本图书对应一个listdiv显示 -->
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="${pageContext.request.contextPath }${book.imgPath }" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span>
									<span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">￥${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<a class="addBK"  id="${book.id }" href="CartServlet?type=addBook&bookId=${book.id }">加入购物车</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		
	</div>
	</div>
		<!-- 引入分页导航栏 -->
		<%@ include file="/WEB-INF/include/nav.jsp" %>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>