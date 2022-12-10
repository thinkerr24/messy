package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 处理用户的图书相关的请求
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();
    /**
     * 按价格查询分页数据的请求方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getPageByPrice(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//1、获取请求参数
    	String pageNumber = request.getParameter("pageNumber");
    	String minPri = request.getParameter("minPri");
    	String maxPri = request.getParameter("maxPri");
    	int size = 4;
    	//2、调用service处理查询的业务
    	Page<Book> page = service.getPageByPrice(pageNumber, size, minPri, maxPri);
    	//将分页的路径和page对象绑定
    	page.setPath(WebUtils.getPath(request));
    	
    	//3、设置到域中共享，再转发到list.jsp页面显示分页数据
    	request.setAttribute("page", page);
    	request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
    }
    
	// 处理查询分页请求的方法 
	protected void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取页码
		String pageNumber = request.getParameter("pageNumber");
		//2、设置每页显示记录条数
		int size = 4;
		//3、调用service处理查询分页的业务逻辑
		Page<Book> page = service.getPage(pageNumber, size);
		//4、将page对象对应的路径和page对象绑定
		String path = WebUtils.getPath(request);
		page.setPath(path);
		//5、将page对象存到request域中共享。
		request.setAttribute("page",page);
		//6、转发到分页数据显示的页面[list.jsp]
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
	}

}
