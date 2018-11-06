package com.jt.common.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 封装业务层分页信息
 * 1) 当前页数据
 * 2) 页码信息
 */
public class PageObject<T> implements Serializable{
	private static final long serialVersionUID = -4288938782658423221L;
	/**存储当前页记录*/
	private List<T> records;
	/**总记录数*/
	private Integer rowCount;
	/**总页数*/
	private Integer pageCount;
	/**页面大小*/
	private Integer pageSize=3;
	/**当前页页码值*/
	private Integer pageCurrent=1;
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
}
