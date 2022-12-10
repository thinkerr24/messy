package com.atguigu.bookstore.bean;
/**
 * 购物车类
 * 	一个购物车中可以有多个购物项
 * 	一个浏览器只能使用一个购物车
 *
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 保存购物项的集合[map可以方便的增删改]
	 * 	- map的key使用的是购物项对应的图书的id
	 */
	private Map<String, CartItem> map = new LinkedHashMap<String , CartItem>();
	/**
	 * 商品总数量
	 */
	private int totalCount;
	/**
	 * 商品的总金额
	 */
	private double totalAmount;
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	//遍历cartItem累加得到
	public int getTotalCount() {
		List<CartItem> list = getCartItemList();
		totalCount = 0;//累加前设置总数量为0
		for (CartItem cartItem : list) {
			totalCount+=cartItem.getCount();
			
		}
		return totalCount;
	}
	public double getTotalAmount() {
		List<CartItem> list = getCartItemList();
		totalAmount = 0;//累加前设置总金额为0
		BigDecimal bd1 = new BigDecimal(totalAmount+"");
		for (CartItem cartItem : list) {
			BigDecimal bd2 = new BigDecimal(cartItem.getAmount()+"");
			bd1 = bd1.add(bd2);
		}
		//将计算后的对象转为double类型
		totalAmount = bd1.doubleValue();
		return totalAmount;
	}
	public Cart() {
		super();
	}
	@Override
	public String toString() {
		return "Cart [map=" + map + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount + "]";
	}
	//将map的值转为list集合的方法
	public List<CartItem> getCartItemList(){
		//将map所有的值存到集合中
		Collection<CartItem> values = map.values();
		//将集合创建为ArrayList
		ArrayList<CartItem> list = new ArrayList<CartItem>(values);
		return list;
	}
	//购物车的业务方法
	//1、添加图书到购物车中
	public void addBook2Cart(Book book) {
		//判断购物车中是否已经存在图书对应的购物项
		CartItem cartItem = map.get(book.getId()+"");
		if(cartItem==null) {
			//图书第一次添加到购物车中
			//将图书转为购物项
			int  count = 1;
			cartItem = new CartItem(book, count);
			//将购物项存到map中[第一次添加时才需要将购物项存到map中]
			map.put(book.getId()+"", cartItem);
		}else {
			//已存在，将购物项的数量+1
			cartItem.setCount(cartItem.getCount()+1);
		}
	}
	//2、删除指定购物项
	public void deleteCartItem(String bookId) {
		map.remove(bookId);
	}
	//3、删除购物车中所有的数据
	public void clearCart() {
		map.clear();
	}
	//4、根据图书id修改指定购物项数量的方法
	public void updateCount(String bookId , String count) {
		CartItem cartItem = map.get(bookId);
		//默认值为修改之前的购物项的数量
		int cou = cartItem.getCount();
		try {
			cou = Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cartItem.setCount(cou);
	}
	
}
