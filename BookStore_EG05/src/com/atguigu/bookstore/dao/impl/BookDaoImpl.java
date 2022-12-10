package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao {

	@Override
	public List<Book> getBooks() {
		// 只要是查询数据并封装为对象，必须保证查询到的数据的字段名和类的属性名一致
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath "
				+ " FROM bs_book";
		return getBeanList(Book.class, sql);
	}

	@Override
	public Book getBookById(String bookId) {
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath"
				+ "  FROM bs_book WHERE id = ?";
		return getBean(Book.class, sql, bookId);
	}

	@Override
	public int saveBook(Book book) {
		String sql = "INSERT INTO bs_book(title , author , price , sales , stock , "
				+ "img_path ) VALUES(? , ? ,? ,? ,? ,?)";
		return update(sql, book.getTitle() , book.getAuthor() , book.getPrice() , book.getSales(),
				book.getStock() , book.getImgPath());
	}

	@Override
	public int deleteBookById(String bookId) {
		String sql = "DELETE FROM bs_book WHERE id = ?";
		return update(sql, bookId);
	}

	@Override
	public int updateBookById(Book book) {
		String sql = "UPDATE bs_book SET title=? , author=? , price=? , sales=? , stock=? , "
				+ " img_path=? WHERE id = ?";
		return update(sql,book.getTitle() , book.getAuthor() , book.getPrice() , book.getSales(),
				book.getStock() , book.getImgPath(), book.getId());
	}

	@Override
	public Page<Book> findPage(Page<Book> page) {
		//目的是为了查询分页需要的数据库数据并设置给page对象
		//1、totalCount
		String countSql = "SELECT COUNT(*) FROM bs_book";
		int count = (int) getCount(countSql);
		page.setTotalCount(count);
		//2、data  分页数据集合
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath "
				+ " FROM bs_book LIMIT ? , ? ";
		List<Book> data = getBeanList(Book.class, sql, page.getIndex() , page.getSize());
		//3、将查询到的data集合设置给page对象
		page.setData(data);
		//4、返回封装完数据的page对象
		return page;
	}

	@Override
	public Page<Book> findPageByPrice(Page<Book> page, double minPrice, double maxPrice) {
		//1、查询价格区间的记录总条数
		String countSql = "SELECT COUNT(*) FROM bs_book WHERE price>= ? AND price<= ? ";
		int totalCount = (int) getCount(countSql,minPrice , maxPrice);
		page.setTotalCount(totalCount);
		//2、查询价格区间的分页数据集合
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath "
				+ " FROM bs_book WHERE  price>= ? AND price<= ? LIMIT ? , ? ";
		List<Book> data = getBeanList(Book.class, sql,minPrice , maxPrice, page.getIndex() , page.getSize());
		page.setData(data);
		return page;
	}

	@Override
	public void batchUpdateStockAndSales(Object[][] params) {
		String sql = "UPDATE bs_book SET  sales=? , stock=?  WHERE id = ?";
		batchUpdate(sql, params);
	}

}
