package com.gcxy.service;

import com.gcxy.mapper.AdminSearch;
import com.gcxy.mapper.myState;
import com.gcxy.pojo.Goods;

import java.util.List;

/**
 * @author HX
 * @title: GoodsService
 * @projectName E_Show
 * @date 2019/7/5  16:41
 */
public interface GoodsService {
	List<Goods> selAll();
	List<Goods> selBySecondName(String secondName);
	Goods selByID(int id);
	List<Goods> selByPage(int pageStart,int pageSize,String name);
	List<Goods> search(myState myState);
	int updGoodsNumber(String goodsid,String number);
	int updGoodsSecondName(String newname,int id);
	int delByGoodsid(int id);
	List<Goods> adminSearch(AdminSearch adminSearch);
	int insNewGoods(Goods goods);
	int updOneGoods(Goods goods);
	int examineSucceess(int goodsid,int number);
}
