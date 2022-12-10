package com.atguigu.bookstore.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCUtils;

public class BaseDao {
	private QueryRunner runner = new QueryRunner();
	
	//增删改的方法
	public int update(String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		int i = 0;
		try {
			i = runner.update(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return i;
	}
	//查询一条记录并封装为指定对象的方法
	public <T>T getBean(Class<T> type , String sql ,Object...params){
		Connection conn = JDBCUtils.getConn();
		T t = null;
		try {
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return t;
	}
	//查询多条记录封装为对象集合的方法
	public <T>List<T> getBeanList(Class<T> type , String sql ,Object...params){
		Connection conn = JDBCUtils.getConn();
		List<T> list = null;
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return list;
	}
	//批量增删该的方法
	public void batchUpdate(String sql , Object[][]params) {
		Connection conn = JDBCUtils.getConn();
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		
	}
	//查询记录条数的方法
	public long getCount(String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		long count = 0;
		try {
			count = (long) runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return count;
	}
}
