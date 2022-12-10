<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<!-- 引入jquery文件 -->
<script type="text/javascript">
	$(function(){
		var i = 0;
		$("#codeImg").click(function(){
			this.src = "${pageContext.request.contextPath}/code.jpg?t="+(i++);
			/*
				浏览器的缓存：
					如果修改后的img标签的src属性值不变，IE+火狐默认使用上次请求的图片缓存直接显示，不重新向服务器发送请求
				解决：欺骗浏览器，在url地址后拼接一个变化的参数
			
			*/
		});
		
		
		
		
		//给提交按钮绑定单击事件
		$("#sub_btn").click(function(){
			//获取用户的注册信息进行验证
			var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			var repwd = $("input[name='repwd']").val();
			var email = $("input[name='email']").val();
			//创建每个验证字符串的正则对象
			var nameReg = /^[a-z0-9_-]{3,16}$/;
			//验证用户名：正则对象提供了test()方法，可以将要验证的字符串传入，如果符合规则返回true，如果不符合返回false
			if(!nameReg.test(username)){
				//用户名不符合规则，阻止提交
				alert("请输入由a-z的小写字母、0-9的数字、下划线或-组成的3到16位的账号!");
				return false;
			}
			var passwordReg = /^[a-z0-9_-]{6,18}$/;
			if(!passwordReg.test(password)){
				alert("请输入由a-z、0-9的数字、下划线或-组成的6到18位的密码!");
				return false;
			}
			//重复密码只需要和password一样即可
			if(repwd!=password){
				alert("两次密码不一致");
				return false;
			}
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/ ;
			if(!emailReg.test(email)){
				alert("邮箱地址错误");
				return false;
			}
			
		});
		
		
		
		// 使用JQuery进行用户名的异步请求校验
		$("[name='username']").change(function(){
			//1. 获取到 输入框中输入的用户名
			var username = this.value;
			//2. 发送异步请求进行校验
			$.ajax({
				url:"UserServlet",  
				data:{"username":username,"type":"checkUsername"},   // UserServlet?type=checkUsername&username=zhangsan
				type:"POST",
				dataType:"json",
				success:function(result){
					// 服务器端会返回:  0 代表可用      1 代表不可用 
					if(result == 0){
						$(".errorMsg").html("用户名可用");
						$("#sub_btn").attr("disabled",false);
						$("#sub_btn").css("background-color","#39987c");
						
					}else{
						$(".errorMsg").html("用户名不可用");
						$("#sub_btn").attr("disabled",true);
						$("#sub_btn").css("background-color","gray");
					}
					
				}
				
			});
			
		});
		
		
		
		
		
		
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
							<%-- <%
								String errorMsg = (String)request.getAttribute("errorMsg");
								if(errorMsg==null){
									//用户第一次打开注册页面，不需要显示提示信息
									errorMsg = "";
								}
							%>
							<span class="errorMsg"><%=errorMsg %></span> --%>
							<%-- <span class="errorMsg"><%=request.getAttribute("errorMsg")==null?"":request.getAttribute("errorMsg") %></span> --%>
							<span class="errorMsg">${errorMsg }</span>
							</div>
							<div class="form">
								<!--
									如果注册页面第一次打开，请求中不包含请求参数
									如果用户注册失败，请求从Servlet转发过来时，请求中就包含了请求参数
								  -->
								<form action="UserServlet" method="post">
									<!-- 通过隐藏域携带请求参数值 -->
									<input type="hidden" name="type" value="regist" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
									<%-- value="<%=username %>" --%> value="${param.username }" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email"
									value="${param.email }" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img id="codeImg" alt="" src="code.jpg" style="width:90px;height:40px; float: right; margin-right: 40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
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