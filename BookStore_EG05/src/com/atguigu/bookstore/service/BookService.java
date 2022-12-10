package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 规定对图书操作的业务方法
 * @author Administrator
 *
 */
public interface BookService {
	/**
	 * 查询所有的图书集合
	 * @return
	 */
	List<Book> getAllBooks();
	/**
	 * 删除图书的业务方法
	 * @param bookId
	 * @return
	 */
	boolean deleteBook(String bookId);
	/**
	 * 添加图书的业务方法
	 * @param book
	 * @return
	 */
	boolean addBook(Book book);
	/**
	 * 查询指定图书的业务方法
	 * @param bookId
	 * @return
	 */
	Book getBook(String bookId);
	/**
	 * 修改指定图书数据的方法
	 * 	book参数，携带了id和修改后的图书数据
	 */
	boolean updateBook(Book book);
	/**
	 * 查询分页的业务方法
	 * @param pageNumber
	 * @param size
	 * @return
	 */
	Page<Book> getPage(String pageNumber,int size);
	/**
	 * 按价格区间查询分页数据的业务方法
	 * @param pageNumber
	 * @param size
	 * @param minPri
	 * @param maxPri
	 * @return
	 */
	Page<Book> getPageByPrice(String pageNumber,int size,String minPri , String maxPri);
	
}
