package com.gcxy.pojo;

/**
 * @author HX
 * @title: Admin
 * @projectName E_Show
 * @date 2019/8/5  12:35
 */
public class Admin {
	private String id;
	private String pwd;
	private String name;
	private int flag;

	private String sex;
	private String borntime;
	private String email;
	private String phone;
	private String address;
	private String postalcode;
	private String registertime;


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBorntime() {
		return borntime;
	}

	public void setBorntime(String borntime) {
		this.borntime = borntime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Admin{" + "id='" + id + '\'' + ", pwd='" + pwd + '\'' + ", name='" + name + '\'' + ", flag=" + flag + ", sex='" + sex + '\'' + ", borntime='" + borntime + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", postalcode='" + postalcode + '\'' + ", registertime='" + registertime + '\'' + '}';
	}
}
