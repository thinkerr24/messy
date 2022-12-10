package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 约束对bs_user表的操作
 *
 */
public interface UserDao {
	//根据账号密码查询用户的方法
	User getUserByUsernameAndPassword(User user);
	//保存用户数据到数据库的方法
	int saveUser(User user);
	
	//根据用户名查询用户对象
	User getUserByUsername(String username);
	
}
