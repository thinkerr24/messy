package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 处理用户订单相关的请求
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderServiceImpl();
	//用户收货的请求方法
	protected void takeGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		int state = 2;
		boolean sendOrTakeGoods = service.sendOrTakeGoods(orderId, state);
		response.sendRedirect(request.getHeader("referer"));
		
	}
	
	/**
	 * 用户查询自己订单的请求方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			//未登录 ， 跳转到登录页面提示用户登录
			request.setAttribute("errorMsg", "登录超时，请重新登录!!!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			List<Order> list = service.getOrders(user.getId());
			//将list存到request域中
			request.setAttribute("list", list);
			//转发到显示订单的页面 /pages/order/order.jsp
			request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
		}
		
	}
	
	
    //处理结账请求   
	protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、不需要参数
		//2、获取session中的user对象，判断是否登录
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
//		if(user==null) {
//			//未登录 ， 跳转到登录页面提示用户登录
//			request.setAttribute("errorMsg", "结账操作必须先登录!!!");
//			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
//		}else {
			//已登录，调用service处理结账业务
			Cart cart = (Cart) session.getAttribute("cart");
			String orderId = service.createOrder(cart, user);
			
			//重定向到结账成功页面 /BookStore_EG05/pages/cart/checkout.jsp
			//将订单id存到session中
			session.setAttribute("orderId", orderId);
			response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
		//}
		
		
	}

}
