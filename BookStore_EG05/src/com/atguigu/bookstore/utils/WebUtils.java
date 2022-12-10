package com.atguigu.bookstore.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;

public class WebUtils {

	/**
	 * 从请求url地址中截取 分页需要的路径的方法
	 * 
	 * @param request
	 * @return
	 */
	public static String getPath(HttpServletRequest request) {
		String uri = request.getRequestURI();// 获取端口号到?之间的地址
		String queryString = request.getQueryString();// 获取？后的请求参数字符串
		if (queryString != null && queryString.contains("&pageNumber")) {
			queryString = queryString.substring(0, queryString.indexOf("&pageNumber"));
		}
		// System.out.println(uri+"?"+queryString);

		return uri + "?" + queryString;
	}

	/**
	 * BeanUtils根据请求参数的key自动将值设置给对象的工具方法 1、请求参数的key必须和对象的属性名一致 2、一个初始化过的对象
	 * 3、数据源[map]
	 * 
	 */
	public static <T> T params2Bean(T t, HttpServletRequest request) {
		// 1、获取数据源
		Map<String, String[]> map = request.getParameterMap();
		// 2、使用BeanUtils将map中的值设置给book对象
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3、返回设置过属性值的book对象
		return t;
	}
}
