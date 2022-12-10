package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class TestBookService {
	private BookService service = new BookServiceImpl();
	@Test
	public void test() {
		//模仿用户的查询分页数据的操作
		
		//1、用户提交分页查询的请求会交给Servlet处理
		String pageNumber = "1";
		int size = 4;
		//2、调用serice处理业务
		Page<Book> page = service.getPage(pageNumber, size);
		
		System.out.println(page);
	}

}
