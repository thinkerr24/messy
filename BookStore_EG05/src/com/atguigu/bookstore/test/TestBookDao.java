package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class TestBookDao {
	private BookDao dao = new BookDaoImpl();
	@Test
	public void testSave() {
		Book book1 = new Book(null, "刘优老师和72男生的故事", "biao哥", "/static/img/default.jpg", 0.1, 72, 1);
		int i = dao.saveBook(book1);
		System.out.println(i);
		Book book2 = new Book(null, "java从入门到转行", "biao哥", "/static/img/default.jpg", 0.2, 72, 1);
		Book book3 = new Book(null, "html5从入门到嫁人", "婷姐", "/static/img/default.jpg", 100, 1000, 10);
		dao.saveBook(book2);
		dao.saveBook(book3);
	}
	@Test
	public void testSelectAll() {
		List<Book> list = dao.getBooks();
		System.out.println(list);
	}
	@Test
	public void testSelect() {
		String bookId = "3";
		Book book = dao.getBookById(bookId);
		System.out.println(book);
	}
	@Test
	public void testUpdate() {
		Book book1 = new Book(1, "刘优老师和72男生不得不说的故事", "彪哥", "/static/img/default.jpg", 0.1, 72, 1);
		int i = dao.updateBookById(book1);
		System.out.println(i);
	}
	@Test
	public void testDelete() {
		String bookId = "1";
		int i = dao.deleteBookById(bookId);
		System.out.println(i);
	}

}
