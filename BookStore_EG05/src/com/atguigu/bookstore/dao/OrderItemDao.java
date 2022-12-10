package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

/**
 * 约束对bs_orderitem表的操作
 * @author Administrator
 *
 */
public interface OrderItemDao {
	/**
	 * 保存订单项的方法
	 * @param item
	 * @return
	 */
	int saveOrderItem(OrderItem item);
	/**
	 * 批量保存订单项的方法
	 * @param params
	 */
	void batchSaveOrderItem(Object[][]params);
	/**
	 * 查询指定订单的订单项集合
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getOrderItemsByOrderId(String orderId);
	
	
	
	
}
