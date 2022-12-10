package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;

/**
 * 处理订单业务的接口
 * @author Administrator
 *
 */
public interface OrderService {
	/**
	 * 创建订单的方法
	 * @param cart
	 * @param user
	 * @return
	 */
	String createOrder(Cart cart , User user);
	/**
	 * 用户查询自己订单的业务方法
	 * @param userId
	 * @return
	 */
	List<Order> getOrders(int userId);
	/**
	 * 管理员查询所有订单的方法
	 * @return
	 */
	List<Order> getAllOrders();
	/**
	 * 管理员发货或用户收货的业务方法
	 * 
	 * 	管理员： state0-1
	 *  用户： state 1-2
	 * @param orderId
	 * @param state
	 * @return
	 */
	boolean sendOrTakeGoods(String orderId , int state);
	

}
