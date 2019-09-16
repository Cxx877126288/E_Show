package com.gcxy.pojo;

import java.util.Date;

/**
 * @author HX
 * @title: LoginTime
 * @projectName E_Show
 * @date 2019/8/1  12:36
 */
public class LoginTime {
	private String userid;
	private Date registertime;
	private Date lastlogintime;
	private int logincount;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getRegistertime() {
		return registertime;
	}

	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public int getLogincount() {
		return logincount;
	}

	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}

	@Override
	public String toString() {
		return "LoginTime{" +
				"userid='" + userid + '\'' +
				", registertime=" + registertime +
				", lastlogintime=" + lastlogintime +
				", logincount=" + logincount +
				'}';
	}
}
