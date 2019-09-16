package com.gcxy.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**用户表
 * @author HX
 * @title: Buyuser
 * @projectName E_Show
 * @date 2019/7/2  10:21
 */
public class Buyuser {
	private int _id;
	private String userid;
	private String username;
	private String userpwd;
	private int sex;
	private Date borntime;
	private String email;
	private String phone;
	private String address;
	private int postalcode;
	private int flag;
	private String register;

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBorntime() {
		return borntime;
	}

	public void setBorntime(Date borntime) {
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

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(borntime);
		return "Buyuser{" +
				"_id=" + _id +
				", userid='" + userid + '\'' +
				", username='" + username + '\'' +
				", userpwd='" + userpwd + '\'' +
				", sex=" + sex +
				", borntime=" + dateString +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", address='" + address + '\'' +
				", postalcode=" + postalcode +
				'}';
	}
}
