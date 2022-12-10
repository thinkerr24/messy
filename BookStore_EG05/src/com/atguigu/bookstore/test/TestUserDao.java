package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class TestUserDao {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void testRegist() {
		//用户在注册页面提交注册信息
		String username = "admin";
		String password = "123456";
		String email = "admin@atguigu.com";
		//将参数获取并封装为user对象
		User user = new User(null, username, password, email);
		//调用UserDAO的方法将数据保存到数据库，保存成功就代表注册成功
		int i = dao.saveUser(user);
		System.out.println(i);
	}
	@Test
	public void testLogin() {
		String username = "admin";
		String password = "123456";
		User user = new User(null, username, password, null);
		
		User loginUser = dao.getUserByUsernameAndPassword(user);
		System.out.println(loginUser);
		
		
	}
}
