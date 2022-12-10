package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物项类
 * 
 * 		一本图书最多只会对应一个购物项
 * @author Administrator
 *
 */
public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 购物项对应的图书信息
	 */
	private Book book;
	/**
	 * 单品购买的数量
	 */
	private int count;
	/**
	 * 单品金额
	 * 	- 计算得到
	 */
	private double amount;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		BigDecimal bd1 = new BigDecimal(getBook().getPrice()+"");
		BigDecimal bd2 = new BigDecimal(getCount()+"");
		BigDecimal result = bd1.multiply(bd2);
		amount = result.doubleValue();
		return amount;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + getAmount() + "]";
	}
	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	
	
	
	
}
