package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	//Service层和数据库交互必须通过Dao层实现
	private BookDao dao = new BookDaoImpl();
	@Override
	public List<Book> getAllBooks() {
		return dao.getBooks();
	}
	@Override
	public boolean deleteBook(String bookId) {
		return dao.deleteBookById(bookId)>0;
	}
	@Override
	public boolean addBook(Book book) {
		return dao.saveBook(book)>0;
	}
	@Override
	public Book getBook(String bookId) {
		return dao.getBookById(bookId);
	}
	@Override
	public boolean updateBook(Book book) {
		return dao.updateBookById(book)>0;
	}
	@Override
	public Page<Book> getPage(String pageNumber, int size) {
		//1、创建分页对象用来携带分页数据
		Page<Book> page = new Page<Book>();
		//类型转换  由于页码是用户从浏览器传入的，有可能不是一个正常的数字类型的字符串，可能有数字转换异常
		int number = 1;//设置默认第一页
		try {
			number = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、将数据设置给page对象
		page.setPageNumber(number);
		page.setSize(size);
		//3、调用dao查询分页数据库的相关数据并设置返回
		Page<Book> findPage = dao.findPage(page);
		return findPage;
	}
	@Override
	public Page<Book> getPageByPrice(String pageNumber, int size, String minPri, String maxPri) {
		//1、创建page对象
		Page<Book> page = new Page<Book>();
		//2、将参数设置给page对象
		int pageNum = 1;
		double minPrice = 0;
		double maxPrice = Double.MAX_VALUE;//设置max的值为最大值，如果用户输入的maxPrivate、参数不是数字查询所有数据
		try {
			pageNum = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			minPrice = Double.parseDouble(minPri);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			maxPrice = Double.parseDouble(maxPri);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		page.setPageNumber(pageNum);
		page.setSize(size);
		//3、调用DAO处理查询
		page = dao.findPageByPrice(page, minPrice, maxPrice);
		//返回查询到的page对象
		return page;
	}
}
