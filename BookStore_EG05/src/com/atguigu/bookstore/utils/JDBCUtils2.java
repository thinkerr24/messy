package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接的帮助工具类
 * @author Administrator
 *
 * 改造工具类:
 * 	   改造目的:  线程与连接绑定。 一个线程中只允许存在一个连接. 
 *   实现:
 *   	 Map： 使用一个线程安全的Map. ConcurrentMap -->ConcurrentHashMap
 *       ThreadLocal : 实现当前线程与某个资源的绑定. 
 * 
 */
public class JDBCUtils2 {
	//c3p0数据库连接池
	private static DataSource source = new ComboPooledDataSource("webDataSource");
	
	private static ThreadLocal<Connection > tl = new ThreadLocal<>();
	
	//获取连接
	public static Connection getConn() {
		// 尝试着从tl中获取连接
		Connection conn = tl.get();
		if(conn == null ) {
			//第一次获取 , 需要从池中拿到一个新的连接， 绑定到tl中
			try {
				conn = source.getConnection();
				
				//将连接与当前线程绑定
				tl.set(conn);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return conn ;
		
		
		/*Connection conn = null;
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;*/
	} 
	
	// 解决当前线程与连接的绑定
	public static  void  releaseConn() {
		//尝试着从tl中获取连接
		Connection conn = tl.get();
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		tl.set(null);
		//tl.remove();
	}
	
	
	
	
	
	//释放连接的方法
	/*public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	
}
