package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 约束对bs_book表的操作
 * @author Administrator
 *
 */
public interface BookDao {
	
	/**
	 * 查询所有图书的方法
	 */
	List<Book> getBooks();
	/**
	 * 查询指定id对应的图书
	 */
	Book getBookById(String bookId);
	/**
	 * 保存图书
	 * 	book参数	除了id没有其他属性都有
	 */
	int saveBook(Book book);
	/**
	 * 删除id对应的图书
	 */
	int deleteBookById(String bookId);
	/**
	 * 根据id更新图书
	 * 	book参数肯定包含图书id和图书的其他信息
	 * 		数据库中根据id查找到需要修改的图书数据，然后使用book对象的所有属性值替换数据库中的数据	
	 * 		
	 */
	int  updateBookById(Book book);
	/**
	 * 查询分页数据的方法
	 * @param page
	 * @return
	 */
	Page<Book> findPage(Page<Book> page);
	/**
	 * 按价格查询分页数据的方法
	 * @param page
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	Page<Book> findPageByPrice(Page<Book> page,double minPrice , double maxPrice);
	/**
	 * 批处理执行的次数
	 * 每次执行批处理的参数列表
	 */
	void batchUpdateStockAndSales(Object[][]params);
}
