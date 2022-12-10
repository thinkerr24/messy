<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加图书</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">添加图书</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
		</div>
		
		<div id="main">
			<!-- 参数可不可以在action地址后模仿get请求参数的方式拼接
				post方式：可以 post请求参数浏览器封装到请求体中
				get方式：不可以 浏览器将请求参数拼接在action地址后使用?连接
			 -->
			<form action="BookManagerServlet" method="post">
				<!-- 隐藏域携带type参数
					浏览器和服务器进行数据交互的传递方式
						1、url地址后拼接
						2、表单中的隐藏域携带
						3、Cookie
						4、会话对象
				 -->
				<input type="hidden" name="type" value="addBook"/>
				<!-- 通过隐藏域给图书携带一个默认的封面地址 -->
				<input type="hidden" name="imgPath" value="/static/img/default.jpg"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="时间简史"/></td>
						<td><input name="price" type="text" value="30.00"/></td>
						<td><input name="author" type="text" value="霍金"/></td>
						<td><input name="sales" type="text" value="200"/></td>
						<td><input name="stock" type="text" value="300"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>