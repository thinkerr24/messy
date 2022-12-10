package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    /**
     * type值和方法名一样   
     * 方法的参数列表一样都是HttpServletRequest和HttpServletResponse
     * 访问的类和调用方法的对象[对象就是调用方法的对象]	
     * 	- 通过方法名和形参类型列表可以在一个类中确定一个方法对象
     * 	- 当前对象可以执行此方法
     * 		反射使用的前提：type属性值必须和方法名一样，方法的参数列表必须和doGet的参数列表一样
     */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//请求时携带需求参数：如果是登录请求提交type=login  
		String type = request.getParameter("type");
		//判断请求的type参数值调用不同的方法处理
		if("login".equals(type)) {
			//System.out.println("用户提交的是登录请求....");
			//login();
			this.login(request, response);
		}else if("regist".equals(type)) {
			//regist();
			this.regist(request, response);
		}
		// A  implements C
		// new A().getClass();
		//去类中确定方法[方法名和参数类型列表以及类]
		Class<? extends UserServlet> cla = this.getClass();
		try {
			//获取方法对象  参数1：方法名  参数2：形参类型列表   request.getClass();不能用
			Method method = cla.getDeclaredMethod(type, HttpServletRequest.class , HttpServletResponse.class);
			//调用方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}*/

	//Servlet处理请求时将请求交给业务层处理
	private UserService service = new UserServiceImpl();
//处理用户的注销功能
protected void logout(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	HttpSession session = request.getSession();
	//销毁session对象
	session.invalidate();
	//跳转回注销之前的页面
	response.sendRedirect(request.getHeader("referer"));
}
	
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2、调用其他类处理业务逻辑[UserDao]
		User user = new User(null, username, password, null);*/
		User user = WebUtils.params2Bean(new User(), request);
		//loginUser是查询到的结果，如果不为null代表登录成功
		//3、调用业务层方法处理业务
		User loginUser = service.login(user);
		if(loginUser==null) {
			request.setAttribute("errorMsg", "账号或密码错误，请重新登录！");
			//登录失败[绝对路径转发到login.html]
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//登录成功
			//将loginUser共享到会话对象中
			HttpSession session = request.getSession();
			session.setAttribute("user", loginUser);
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取用户提交的验证码字符串
		String code = request.getParameter("code");
		//2、获取KaptchaServlet在session中设置的验证码
		HttpSession session = request.getSession();
		//session.getAttribute("KAPTCHA_SESSION_KEY"); 默认key
		String serverCode =  (String) session.getAttribute("codeKey");
		
		//3、比较验证码是否正确
		if(serverCode!=null && serverCode.equals(code)) {
			//可以注册
			User user = WebUtils.params2Bean(new User(), request);
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
		}else {
			//重复提交或验证码错误，设置提示消息并转发到注册页面让用户继续注册
			//注册失败设置的错误消息
			request.setAttribute("errorMsg", "验证码错误！");
			//注册失败
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
		//4、移除服务器session中保存的验证码
		session.removeAttribute("codeKey");
	}
	
	
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取到异步请求提交的用户名
		String username = request.getParameter("username");
		
		PrintWriter out = response.getWriter();
		
		boolean flag = service.checkUsername(username);
		
		
		System.out.println("flag: " +flag );
		
		if(flag) {
			out.println("0");
		}else {
			out.println("1");
		}
		
	}
	
}
