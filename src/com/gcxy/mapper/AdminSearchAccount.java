package com.gcxy.mapper;

/**
 * @author HX
 * @title: AdminSearchAccount
 * @projectName E_Show
 * @date 2019/8/18  14:01
 */
public class AdminSearchAccount {
	private String userid;
	private String starttime;
	private String endtime;
	private int flag;

	public AdminSearchAccount() {
		flag = 2;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
