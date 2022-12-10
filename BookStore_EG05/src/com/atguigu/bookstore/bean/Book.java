package com.atguigu.bookstore.bean;

import java.io.Serializable;

/**
 * bs_book表的实体类
 * @author Administrator
 *
 */
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图书id
	 */
	private Integer id ;
	/**
	 * 图书标题
	 */
	private String title;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 封面图片路径
	 */
	private String imgPath;
	/**
	 * 单价
	 */
	private double price;
	/**
	 * 销量
	 */
	private int sales;
	/**
	 * 库存
	 */
	private int stock;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Book(Integer id, String title, String author, String imgPath, double price, int sales, int stock) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.imgPath = imgPath;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", imgPath=" + imgPath + ", price="
				+ price + ", sales=" + sales + ", stock=" + stock + "]";
	}
	
	

}
