package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl  extends BaseDao implements OrderDao{

	@Override
	public int saveOrder(Order order) {
		String sql = "INSERT INTO bs_order(id , order_time , state , total_count , total_amount "
				+ ", user_id ) VALUES(? ,? ,? ,? ,? ,? )";
		return update(sql, order.getId() , order.getOrderTime() , order.getState() ,
				order.getTotalCount() , order.getTotalAmount() , order.getUserId());
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		String sql = "SELECT id , order_time orderTime, state , total_count totalCount, "
				+ "total_amount totalAmount, user_id userId "
				+ " FROM bs_order WHERE user_id = ? ORDER BY order_time DESC";
		return getBeanList(Order.class, sql, userId);
	}

	@Override
	public List<Order> getAllOrders() {
		String sql = "SELECT id , order_time orderTime, state , total_count totalCount, "
				+ "total_amount totalAmount, user_id userId "
				+ " FROM bs_order ORDER BY order_time DESC";
		return getBeanList(Order.class, sql);
	}

	@Override
	public int updateOrderStateByOrderId(String orderId, int state) {
		String sql = "UPDATE bs_order SET state = ? WHERE id = ?";
		return update(sql, state , orderId);
	}

}
