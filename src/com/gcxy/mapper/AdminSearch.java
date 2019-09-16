package com.gcxy.mapper;

/**
 * @author HX
 * @title: AdminSearch
 * @projectName E_Show
 * @date 2019/8/7  14:32
 */
public class AdminSearch {
	private String catename;
	private String second_name;
	private double low_price;
	private double high_price;
	private String keyword;

	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public int getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	private int pagenumber;
	private int pagesize;

	public double getHigh_price() {
		return high_price;
	}

	public void setHigh_price(double high_price) {
		this.high_price = high_price;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public double getLow_price() {
		return low_price;
	}

	public void setLow_price(double low_price) {
		this.low_price = low_price;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "AdminSearch{" + "second_name='" + second_name + '\'' + ", low_price=" + low_price + ", high_price=" + high_price + ", keyword='" + keyword + '\'' + '}';
	}
}
