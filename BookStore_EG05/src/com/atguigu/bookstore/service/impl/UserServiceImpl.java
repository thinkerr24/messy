package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;
/**
 * service层需要和数据库交互，通过dao层实现
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	@Override
	public User login(User user) {
		return dao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		return dao.saveUser(user)>0;
	}
	
	@Override
	public boolean checkUsername(String username) {
		
		return dao.getUserByUsername(username) == null ;
	}

}
