package com.gcxy.pojo;

import java.util.Date;

/**
 * @author HX
 * @title: Account
 * @projectName E_Show
 * @date 2019/8/2  12:30
 */
public class Account {
	private String _id;
	private String userid;
	private String goodsid;
	private String number;
	private String payway;
	private String getway;
	private String adminid;
	private String examinetime;
	private String examineinfor;
	private int examineflag;
	private String address;
	private int postalcode;
	private String phone;
	private String email;
	private String name;
	private String taketime;

	public String getTaketime() {
		return taketime;
	}

	public void setTaketime(String taketime) {
		this.taketime = taketime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public String getGetway() {
		return getway;
	}

	public void setGetway(String getway) {
		this.getway = getway;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getExaminetime() {
		return examinetime;
	}

	public void setExaminetime(String examinetime) {
		this.examinetime = examinetime;
	}

	public String getExamineinfor() {
		return examineinfor;
	}

	public void setExamineinfor(String examineinfor) {
		this.examineinfor = examineinfor;
	}

	public int getExamineflag() {
		return examineflag;
	}

	public void setExamineflag(int examineflag) {
		this.examineflag = examineflag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Account{" + "_id='" + _id + '\'' + ", userid='" + userid + '\'' + ", goodsid='" + goodsid + '\'' + ", number='" + number + '\'' + ", payway='" + payway + '\'' + ", getway='" + getway + '\'' + ", adminid='" + adminid + '\'' + ", examinetime=" + examinetime + ", examineinfor='" + examineinfor + '\'' + ", examineflag=" + examineflag + ", address='" + address + '\'' + ", postalcode=" + postalcode + ", phone='" + phone + '\'' + ", email='" + email + '\'' + ", name='" + name + '\'' + ", taketime=" + taketime + '}';
	}
}
