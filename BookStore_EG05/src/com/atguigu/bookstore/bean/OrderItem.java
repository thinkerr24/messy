package com.atguigu.bookstore.bean;
/**
 * 订单项类
 * 		一个订单可以有多个订单项，通过外键orderId关联
 * 		一个购物项对应一个订单项对象
 * 	bs_orderitem表的实体类
 * @author Administrator
 *
 */
public class OrderItem {
	/**
	 * 订单项id
	 */
	private Integer id;
	/**
	 * 图书和订单项相关的数据
	 */
	private String title;
	private String author;
	private String imgPath;
	private int count;
	private double amount;
	private double price;
	/**
	 * 订单项所属订单的id
	 */
	private String orderId;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public OrderItem(Integer id, String title, String author, String imgPath, int count, double amount, double price,
			String orderId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.imgPath = imgPath;
		this.count = count;
		this.amount = amount;
		this.price = price;
		this.orderId = orderId;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", title=" + title + ", author=" + author + ", imgPath=" + imgPath + ", count="
				+ count + ", amount=" + amount + ", price=" + price + ", orderId=" + orderId + "]";
	}
	
	
	
}
