package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

/**
 * 约束用户相关操作的业务方法
 * @author Administrator
 *
 */
public interface UserService {
	
	//处理登录业务的方法
	User login(User user);
	
	//处理注册业务的方法
	boolean regist(User user);
	
	//处理注册业务中  异步用户名的校验
	boolean checkUsername(String username);
	
}
