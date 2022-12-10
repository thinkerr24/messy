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
 * 处理登录请求
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private UserDao dao = new UserDaoImpl();   
	//Servlet处理请求时将请求交给业务层处理
	private UserService service = new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2、调用其他类处理业务逻辑[UserDao]
		User user = new User(null, username, password, null);
		//loginUser是查询到的结果，如果不为null代表登录成功
		//3、调用业务层方法处理业务
		User loginUser = service.login(user);
		if(loginUser==null) {
			request.setAttribute("errorMsg", "账号或密码错误，请重新登录！");
			//登录失败[绝对路径转发到login.html]
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//登录成功
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
		
		
		/*User loginUser = dao.getUserByUsernameAndPassword(user);
		//3、根据处理结果给用户响应[登录成功重定向到成功页面，失败转发到login.html页面]
		if(loginUser==null) {
			//登录失败[绝对路径转发到login.html]
			request.getRequestDispatcher("/pages/user/login.html").forward(request, response);
		}else {
			//登录成功
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.html");
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
