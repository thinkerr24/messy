package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public String createOrder(Cart cart, User user) {
		//1、将购物车转为订单对象存到数据库中
		//1.1 订单id   时间戳+"暗号"+用户id
		String id = System.currentTimeMillis()+""+user.getId();
		//1.2 订单创建的时间   当前时间点
		Date orderTime = new Date();
		//1.3 订单的状态  默认未发货0
		int state = 0;
		Order order = new Order(id, orderTime, state, cart.getTotalCount(), cart.getTotalAmount(), user.getId());
		//1.4 调用orderDao将order存到bs_order表
		int saveOrder = orderDao.saveOrder(order);
		
		//2、将购物车的购物项集合转为订单项存到数据库中[购物项和订单项一一对应]
		List<CartItem> list = cart.getCartItemList();
		//遍历时封装批处理需要的二维数组
		Object[][] bookParams = new Object[list.size()][];
		Object[][] orderItemParams = new Object[list.size()][];
		int i = 0;
		//遍历购物项
		for (CartItem cartItem : list) {
			//将每个遍历的购物项创建为订单项存到数据库中
			Book book = cartItem.getBook();
			/*OrderItem item = new OrderItem(null, book.getTitle(), book.getAuthor(), book.getImgPath(),
					cartItem.getCount(), cartItem.getAmount(), book.getPrice(), id);*/
			//title , author , img_path , price , amount , count ,order_id
			orderItemParams[i] = new Object[] {book.getTitle(), book.getAuthor(), 
					book.getImgPath(),book.getPrice(),cartItem.getAmount(),cartItem.getCount(),id};
			
			
			//调用orderItemDaoImpl的方法保存订单项
			//orderItemDao.saveOrderItem(item);
			//4、修改图书销量库存
			//每个购物项对应一本图书，在创建订单项时修改对应的图书销量库存
			book.setSales(book.getSales()+cartItem.getCount());//原有销量+购物项的数量
			book.setStock(book.getStock()-cartItem.getCount());//原有库存-购物项数量
			//判断图书的库存是否为负数，如果为负数手动抛出异常
			if(book.getStock()<0) {
				throw new RuntimeException("库存不能为负数！");
			}
			//调用BookDao去数据库中更新图书的销量库存
			//bookDao.updateBookById(book);
			//sales=? , stock=?  WHERE id = ? 
			bookParams[i] = new Object[] {book.getSales() , book.getStock() , book.getId()};
			i++;
		}
		//参数封装完毕，可以执行批处理
		orderItemDao.batchSaveOrderItem(orderItemParams);
		bookDao.batchUpdateStockAndSales(bookParams);
		
		//3、清空购物车
		cart.clearCart();
		return id;
	}
	
	@Override
	public List<Order> getOrders(int userId) {
		return orderDao.getOrdersByUserId(userId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	@Override
	public boolean sendOrTakeGoods(String orderId, int state) {
		return orderDao.updateOrderStateByOrderId(orderId, state)>0;
	}

}
