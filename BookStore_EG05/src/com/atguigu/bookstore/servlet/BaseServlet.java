package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 要求
 * 1、项目中所有的Servlet都继承BaseServlet
 * 2、Servlet的方法名必须和type值一样
 * 3、Servlet的方法参数必须和doGet方法结构一样
 * 4、Servlet子类不能重写doGet和doPost
 * 	UserServlet extends BaseServlet
 * 		- 用户访问时
 * 		- 服务器调用生命周期方法
 * 			service
 * 			doGet[调用离UserServlet最近的， 如果UserServlet实现了则调用UserServlet的]
 */
public class BaseServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决post请求乱码:在使用request对象之前
		//request.setCharacterEncoding("UTF-8");
		//获取type参数： 对应方法名
		String type = request.getParameter("type");
		//获取子类的类型: this:BaseServlet不会创建对象，this代表子类的servlet的对象
		Class<? extends BaseServlet> cla = this.getClass();
		//获取类模板中的type方法
		try {
			Method method = cla.getDeclaredMethod(type, HttpServletRequest.class , HttpServletResponse.class);
			//调用方法
			method.invoke(this, request , response);
		} catch (Exception e) {
			//e.printStackTrace();
			
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
