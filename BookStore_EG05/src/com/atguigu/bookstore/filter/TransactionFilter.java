package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 *  事务过滤器：
 *  	一次请求与响应期间，实质上一个线程. 在一个线程中绑定了一个连接。
 *  
 *  Spring 框架:  声明式事务管理 
 */
public class TransactionFilter  extends HttpFilter{
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//给当前的线程绑定一个连接. 
		Connection  conn = JDBCUtils.getConn();
		
		try {
			//取消默认的自动提交
			conn.setAutoCommit(false);
			
			//放行
			chain.doFilter(request, response);
			
			
			//提交
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			//回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//去往错误页面. 
			response.sendRedirect(request.getContextPath() + "/pages/error/error.jsp");
			
		}finally {
			//释放连接
			JDBCUtils.releaseConn();
		}
		
	}

}
