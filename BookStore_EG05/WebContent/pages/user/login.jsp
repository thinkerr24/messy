<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<!-- 使用base标签给相对路径指定基准路径 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<!-- 先获取request域中的错误消息:  -->
								<%-- <%
									String errorMsg = (String)request.getAttribute("errorMsg");
									if(errorMsg==null){
										//用户第一次打开登录页面，没有错误消息，给第一次打开的提示
										errorMsg = "请输入用户名和密码";
									}
								%>
								<span class="errorMsg"><%=errorMsg %></span> --%>
								<span class="errorMsg">${empty requestScope.errorMsg?"请输入用户名和密码11":errorMsg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<!-- 给登录页面的收集登录信息的表单中加入type参数描述请求，UserServlet可以根据type值调用指定方法处理请求 -->
									<!-- 需要提交给服务器的参数有不希望用户看到，可以使用隐藏域携带 -->
									<input type="hidden" name="type" value="login"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>