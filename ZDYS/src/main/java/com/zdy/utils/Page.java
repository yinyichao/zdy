package com.zdy.utils;

import java.util.List;

public class Page<T> {
	
	public static final int LIMIT_NO_PAGINATION = -1;
	/** 总记录数 */
	private Long total;
	
	private int totalPage;//总页 (需要) yins
	
	/** 分页结果 */
	private List<T> root;
	/** 开始页码 */
	private int start;
	/** 每页多少 */
	private int limit;
	
	private String sort;

	private String dir; // 枚举
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRoot() {
		return root;
	}

	public void setRoot(List<T> root) {
		this.root = root;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}