package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理用户注册请求的servlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//     private UserDao dao = new UserDaoImpl();  
	private UserService service = new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		//2、调用UserDao处理请求
		User user = new User(null, username, password, email);
		boolean b = service.regist(user);
		if(b) {
			//注册成功
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
		}else {
			//注册失败设置的错误消息
			request.setAttribute("errorMsg", "用户名已存在！");
			//注册失败
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
		
		
		/*int i = dao.saveUser(user);
		//3、根据处理结果给用户响应
		if(i>0) {
			//注册成功
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.html");
		}else {
			//注册失败
			request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
		}*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
