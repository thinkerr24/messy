package com.atguigu.bookstore.bean;
/**
 * 订单类： 一个用户可以有多个订单
 * 		一个订单对应一个购物车
 *	对应bs_order表的数据
 */

import java.util.Date;

public class Order {
	/**
	 * 订单id
	 */
	private String id ; 
	/**
	 * 订单创建的时间
	 */
	private Date orderTime;
	/**
	 * 订单状态  收发货就是改变订单的状态值
	 * 		0： 未发货
	 * 		1：已发货
	 * 		2：交易完成
	 */
	private int state;
	/**
	 * 订单商品总数量
	 */
	private int totalCount;
	/**
	 * 订单商品总金额
	 */
	private double totalAmount;
	/**
	 * 订单所属用户的id
	 */
	private int userId;
	public Order(String id, Date orderTime, int state, int totalCount, double totalAmount, int userId) {
		super();
		this.id = id;
		this.orderTime = orderTime;
		this.state = state;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
		this.userId = userId;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", state=" + state + ", totalCount=" + totalCount
				+ ", totalAmount=" + totalAmount + ", userId=" + userId + "]";
	}
	
	
}
