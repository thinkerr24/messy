package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 *
 */
public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分页需要显示的数据集合
	 */
	private List<T> data;
	/**
	 * 当前分页的页码
	 */
	private int pageNumber;
	/**
	 * 分页需要显示几条记录
	 */
	private int size;
	/**
	 * 分页数据查询的起始索引
	 * 	- 根据pageNumber和size计算得到
	 */
	private int index;
	/**
	 * 数据的总记录条数
	 */
	private int totalCount;
	/**
	 * 总页码
	 * 	- 根据totalCount和size计算得到
	 */
	private int totalPage;
	/**
	 * 分页访问的url路径
	 */
	private String path;
	
	
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPageNumber() {
		if(pageNumber < 1) {
			return 1;
		}else if(pageNumber> getTotalPage()){
			return getTotalPage();
		}
		
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	//计算得到
	public int getIndex() {
		return (getPageNumber()-1)*getSize();
	}
	/*public void setIndex(int index) {
		this.index = index;
	}*/
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	//计算得到
	/**
	 *  
	 *  totalPage		totalCount		size
	 *  10/4+1			10				4
	 *  10/5			10				5
	 *  10/20+1			10  			20
	 *  
	 * @return
	 */
	public int getTotalPage() {
		if(getTotalCount()%getSize()==0) {
			totalPage = getTotalCount()/getSize();
		}else {
			totalPage = getTotalCount()/getSize()+1;
		}
		
		return totalPage;
	}
	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
	@Override
	public String toString() {
		return "Page [data=" + data + ", pageNumber=" + pageNumber + ", size=" + size + ", index=" + getIndex()
				+ ", totalCount=" + totalCount + ", totalPage=" + getTotalPage()
	+ "]";
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
