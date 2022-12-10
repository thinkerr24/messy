package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 处理管理员请求的servlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private OrderService service = new OrderServiceImpl();
	//查询所有订单的方法
	protected void getAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> list = service.getAllOrders();
		request.setAttribute("list", list);
		// /pages/manager/order_manager.jsp
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
		
	}
	//发货功能
	protected void sendGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取要修改的订单的id
		String orderId = request.getParameter("orderId");
		int state = 1;
		//2、调用service处理业务
		boolean b = service.sendOrTakeGoods(orderId, state);
		//跳转回之前的页面
		response.sendRedirect(request.getHeader("referer"));
		
	}
}
