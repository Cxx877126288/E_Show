package com.gcxy.pojo;

/**
 * @author HX
 * @title: Secondca
 * @projectName E_Show
 * @date 2019/7/4  14:44
 */
public class Secondca {
	private int _id;
	private String follow_name;
	private String secondname;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getFollow_name() {
		return follow_name;
	}

	public void setFollow_name(String follow_name) {
		this.follow_name = follow_name;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	@Override
	public String toString() {
		return "Secondca{" +
				"_id=" + _id +
				", follow_name='" + follow_name + '\'' +
				", secondname='" + secondname + '\'' +
				'}';
	}
}
