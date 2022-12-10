package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

/**
 * 处理用户购物项相关的请求
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService service = new BookServiceImpl();
	//处理修改购物项数量的请求方法
	protected void updateCount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String count = request.getParameter("count");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.updateCount(bookId, count);
		//跳转回之前的页面
		response.sendRedirect(request.getHeader("referer"));
	}
	
	
	//处理清空购物车的请求
	protected void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.clearCart();
		//跳转回之前的页面
		response.sendRedirect(request.getHeader("referer"));
		
	}
	
	
	//处理删除购物项请求的方法
	protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取要删除的购物项图书的id
		String bookId = request.getParameter("bookId");
		HttpSession session = request.getSession();
		Cart cart  = (Cart) session.getAttribute("cart");
		//调用购物车的删除方法
		cart.deleteCartItem(bookId);
		//跳转回之前的页面
		response.sendRedirect(request.getHeader("referer"));
		
	}
	
	
	//处理添加图书到购物车的请求   
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取要添加的图书的id
		String bookId = request.getParameter("bookId");
		Book book = service.getBook(bookId);
		//2、判断session中是否存在购物车对象
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null) {
			//浏览器第一次使用购物车，需要新创建
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//将图书添加到购物车中
		cart.addBook2Cart(book);
		
		
		//将添加的图书的标题共享，存到session域中
		session.setAttribute("title", book.getTitle());
		//跳转回添加之前的页面
		//response.sendRedirect(request.getHeader("referer"));
		
		//将页面中需要的数据 通过json的格式返回
		//1. 购物车中的书的总数    2. 当前添加的书的名字
		Map<String,String> map = new HashMap<>();
		map.put("totalCount",cart.getTotalCount()+"" );
		map.put("title", book.getTitle());
		
		Gson gson = new Gson();
		
		String jsonStr = gson.toJson(map);
		
		PrintWriter out  = response.getWriter();
		
		out.println(jsonStr);
		
		out.close();
		
		System.out.println("addBook 执行完毕.......");
	}

}
