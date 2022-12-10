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
 *       ThreadLocal
 * 
 */
public class JDBCUtils {
	//c3p0数据库连接池
	private static DataSource source = new ComboPooledDataSource("webDataSource");
	
	// 使用Map进行 当前线程与 连接的绑定. 
	private static ConcurrentHashMap<Thread, Connection> cm  = new ConcurrentHashMap<Thread, Connection>();
	
	//获取连接
	public static Connection getConn() {
		//每次要获取连接之前，需要从Map中尝试获取当前线程中绑定的连接.
		Connection conn = cm.get(Thread.currentThread());
		if(conn == null) {
			//第一次获取，  从池中获取一个新连接，再绑定到线程上. 
			try {
				conn = source.getConnection();
				cm.put(Thread.currentThread(), conn);
			} catch (SQLException e) {
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
		Connection conn = cm.get(Thread.currentThread());
		if(conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//将当前线程 从Map中移除掉.
		cm.remove(Thread.currentThread());
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
