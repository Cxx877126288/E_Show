package com.gcxy.service;

import com.gcxy.pojo.Buycar;

import java.util.List;

/**
 * @author HX
 * @title: BuycarService
 * @projectName E_Show
 * @date 2019/7/9  9:19
 */
public interface BuycarService {
	List<Buycar> selAll();
	List<Buycar> selOneUserCarByID(String id);
	int insOne(Buycar buycar);
	int delOneGoods(String carid);
	int updGoodsnumber(String carid,String goodsnumber);
	Buycar selOneByGoodsidAndUserid(String goodsid,String userid);
	int addSameGoods(String userid,String goodsid);
	int delByUseridAndGoodsid(String userid,int goodsid);
	int delByGoodsid(int goodsid);
}
