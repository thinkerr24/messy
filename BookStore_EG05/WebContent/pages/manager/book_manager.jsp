<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@ include file="/WEB-INF/include/base.jsp" %>

<script type="text/javascript">
	/* 给删除超链接绑定单击事件 */	
	$(function(){
		$(".delA").click(function(){
			//由于a标签遍历显示图书时会产生多个，所以不能使用id属性
			//获取被点击的a标签所在行的第一个单元格的文本内容
			var title = $(this).parents("tr").children("td:eq(0)").text();
			if(!confirm("你真的要删除《"+title+"》吗？")){
				//取消删除，阻止a标签的默认行为
				return false;
			}
		});
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty requestScope.page.data }">
			<%-- ${empty requestScope.list } 判断查询所有的图书集合 --%>
				<h3 style="color:red; text-align: center;margin-top: 150px;">没有图书数据！赶紧去添加吧！<a href="pages/manager/book_add.jsp">点击添加图书</a></h3>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<!-- 遍历图书集合，每本图书数据对应一行显示 -->
					<c:forEach items="${page.data }" var="book">
						<tr>
							<td>${book.title }</td>
							<td>${book.price }</td>
							<td>${book.author }</td>
							<td>${book.sales }</td>
							<td>${book.stock }</td>
							<td><a href="BookManagerServlet?type=getBook&bookId=${book.id }">修改</a></td>
							<td><a class="delA" href="BookManagerServlet?type=deleteBook&bookId=${book.id }">删除</a></td>
						</tr>	
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
					</tr>	
				</table>
			</c:otherwise>
		</c:choose>
		
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