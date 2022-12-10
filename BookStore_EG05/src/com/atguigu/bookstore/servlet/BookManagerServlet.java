package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 处理管理员和图书相关的请求
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//servlet处理业务和数据库交互都是通过service层实现
	private BookService service = new BookServiceImpl();
	/**
	 * 查询分页请求的方法
	 * 		取代之前的查询所有图书数据的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求当前方法的url地址，设置到域中共享，再转发到book_manager.jsp页面时取出变量使用
		String path = WebUtils.getPath(request);//给page对象添加一个新的属性path，用来绑定分页访问的路径
		//1、获取请求参数[页码]
		String pageNumber = request.getParameter("pageNumber");//用户表单中的数据以报文形式提交
	//	request.getAttribute(name) 项目中不同资源之间跳转时如果需要数据共享使用域
		//2、设置每页显示的记录条数
		int size = 4;
		//3、调用service处理业务
		Page<Book> page = service.getPage(pageNumber, size);
		//将path设置给page对象
		page.setPath(path);
		//4、存到request域中共享
		request.setAttribute("page", page);
		//5、转发到book_manager.jsp去显示分页数据
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
	}
	
	/**
	 * 处理修改图书的请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取修改后的图书数据
		Book book = WebUtils.params2Bean(new Book(), request);//book的id在数据库中一定会对应一本图书
		//2、调用Service处理修改业务
		boolean b = service.updateBook(book);
		//3、根据处理结果给用户响应
		//跳转到图书显示的页面
		//response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getPage");
		//跳转回修改之前的页面
		//获取表单提交的ref参数[修改之前的页面路径]
		String ref = request.getParameter("ref");
		response.sendRedirect(ref);
	}
	/**
	 * 查询指定图书
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1、获取请求参数
		String bookId = req.getParameter("bookId");
		//2、调用service处理业务
		Book book = service.getBook(bookId);
		//获取修改之前的页面地址
		String referer = req.getHeader("referer");
		//将地址存到request中
		req.setAttribute("ref", referer);
		//3、将book对象存到域中共享
		req.setAttribute("book", book);
		//4、转发到修改图书的页面
		req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
	}
	
	
	/**
	 * 处理添加图书的请求
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBook(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		//1、获取请求参数
		//参数1：要设置属性的对象   参数2：数据源map[map中的key和对象的属性名必须对应]
		Book book = WebUtils.params2Bean(new Book(), request);
		System.out.println(book);
		//2、调用service处理业务
		boolean b = service.addBook(book);
		//3、根据处理结果给用户响应
		//重定向到BookManagerServlet.getAllBooks
		//重定向到查询分页数据的方法
		resp.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getPage");
	}
	
	/**
	 * 查询所有图书的请求
	 */
	protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		//2、调用其他类处理业务
		List<Book> list = service.getAllBooks();
		//将数据存到域中共享
		request.setAttribute("list", list);
		//3、给用户响应[转发(如果需要使用request域使用转发)、重定向、response对象向响应体写入内容]
		//将集合数据交给book_manager.jsp页面显示
		//转发到book_manager.jsp
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	/**
	 * 处理删除图书的请求
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	//此方法被访问时是从book_manager.jsp页面点击删除超链接跳转过来的，所以此方法的上一个页面地址就是删除之前的页面地址
	protected void deleteBook(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		//1、获取要删除的图书id
		String bookId = request.getParameter("bookId");
		//2、调用BookService处理删除的业务
		boolean b = service.deleteBook(bookId);
		//3、跳转回删除之前的页面给用户响应
		//获取上个页面地址
		String referer = request.getHeader("referer");
		//如果使用referer只能用重定向
		System.out.println(referer);
		resp.sendRedirect(referer);
	}
	
}
