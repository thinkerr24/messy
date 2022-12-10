package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class TestBigDecimal {

	@Test
	public void test() {
		double d1 = 0.1;
		double d2 = 0.2;
		System.out.println(d1+d2);
	}
	@Test
	public void test2() {
		//解决小数精度问题
		double d1 = 0.1;
		double d2 = 0.2;
		//1、将小数转为BigDecimal对象[不能调用double类型的构造器 ，使用String类型的构造器]
		BigDecimal bd1 = new BigDecimal(d1+"");
		BigDecimal bd2 = new BigDecimal(d2+"");
		//2、调用对象的计算的方法
		BigDecimal bd = bd1.add(bd2);
		System.out.println(bd);
		//3、将计算后的对象转为double类型
		double result = bd.doubleValue();
		System.out.println(result);
	}

}
