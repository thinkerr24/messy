package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *   登录过滤器:  解决当前项目中需要进行登录判断的所有的需求，都可以使用LoginFilter来处理.
 */
public class LoginFilter extends HttpFilter {
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("user");
		
		if(obj == null ) {
			//未登录
			request.setAttribute("errorMsg", "结账操作必须先登录!!!!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//登录
			chain.doFilter(request, response);
		}
		
	}
}
