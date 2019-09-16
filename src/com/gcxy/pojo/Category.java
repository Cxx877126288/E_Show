package com.gcxy.pojo;

/**
 * @author HX
 * @title: Category
 * @projectName E_Show
 * @date 2019/7/4  14:07
 */
public class Category {
	private int _id;
	private String commodityname;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCommodityname() {
		return commodityname;
	}

	public void setCommodityname(String commodityname) {
		this.commodityname = commodityname;
	}

	@Override
	public String toString() {
		return "Category{" +
				"_id=" + _id +
				", commodityname='" + commodityname + '\'' +
				'}';
	}
}
