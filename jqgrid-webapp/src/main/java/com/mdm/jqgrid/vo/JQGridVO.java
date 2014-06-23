package com.mdm.jqgrid.vo;

import java.util.List;

/**
 * <pre>
 * <p>페이징의 목적으로 만들어진 VO클래스</p>
 * 
 * 페이징을 하기 위한 정보를 담고 있으나, 사실상
 * JQGrid의 페이징처리를 위하여 변수명이 맞추어져 있음.
 * 
 * JQGrid의 페이징처리에 관련된 변수명은 다음과 같음
 * 
 * _search=true
 * nd=1386307226951
 * rows=10
 * page=1
 * sidx=create_date
 * sord=desc
 * searchField=job_id
 * searchString=JOB-2
 * searchOper=eq
 * filters=
 * </pre>
 * @author developer
 *
 */
public class JQGridVO {
	private int page;//페이지
	private int rows;//한페이지에 표시될 레코드수
	private String sidx;//sort할 컬럼
	private String sord;//asc, desc
	private long nd;//서버에 요청한 time
	private boolean _search;//조건검색을 하였는가
	
	private String searchField;
	private String searchString;
	private String searchOper;
	
	private List<?> root;
	private int total;
	private int records;
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public long getNd() {
		return nd;
	}
	public void setNd(long nd) {
		this.nd = nd;
	}
	public boolean is_search() {
		return _search;
	}
	public void set_search(boolean _search) {
		this._search = _search;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public List<?> getRoot() {
		return root;
	}
	public void setRoot(List<?> root) {
		this.root = root;
	}
	
}
