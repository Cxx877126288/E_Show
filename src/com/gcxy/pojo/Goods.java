package com.gcxy.pojo;

/**
 * @author HX
 * @title: Goods
 * @projectName E_Show
 * @date 2019/7/5  16:37
 */
public class Goods {
	private int goodsid;
	private String second_name;
	private String goodsname;
	private double goodsprice;
	private int goodsnumber;
	private String goodsinfor;
	private String goodsimage;

	public String getGoodsimage() {
		return goodsimage;
	}

	public void setGoodsimage(String goodsimage) {
		this.goodsimage = goodsimage;
	}

	public String getGoodsinfor() {
		return goodsinfor;
	}

	public void setGoodsinfor(String goodsinfor) {
		this.goodsinfor = goodsinfor;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public double getGoodsprice() {
		return goodsprice;
	}

	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}

	public int getGoodsnumber() {
		return goodsnumber;
	}

	public void setGoodsnumber(int goodsnumber) {
		this.goodsnumber = goodsnumber;
	}

	@Override
	public String toString() {
		return "Goods{" +
				"goodsid=" + goodsid +
				", second_name='" + second_name + '\'' +
				", goodsname='" + goodsname + '\'' +
				", goodsprice=" + goodsprice +
				", goodsnumber=" + goodsnumber +
				", goodsinfor='" + goodsinfor + '\'' +
				", goodsimage='" + goodsimage + '\'' +
				'}';
	}
}
