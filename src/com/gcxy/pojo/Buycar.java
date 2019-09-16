package com.gcxy.pojo;

/**
 * @author HX
 * @title: Buycar
 * @projectName E_Show
 * @date 2019/7/9  9:18
 */
public class Buycar {
	private int _id;
	private String userid;
	private int goodsid;
	private double goodsnumber;
	public double getGoodsnumber() {
		return goodsnumber;
	}
	public void setGoodsnumber(double goodsnumber) {
		this.goodsnumber = goodsnumber;
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

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	@Override
	public String toString() {
		return "Buycar{" +
				"_id=" + _id +
				", userid='" + userid + '\'' +
				", goodsid=" + goodsid +
				", goodsnumber=" + goodsnumber +
				'}';
	}
}
