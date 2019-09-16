package com.gcxy.service.impl;

import com.gcxy.mapper.BuycarMapper;
import com.gcxy.pojo.Buycar;
import com.gcxy.service.BuycarService;

import java.util.List;


/**
 * @author HX
 * @title: BuycarServiceImpl
 * @projectName E_Show
 * @date 2019/7/9  9:19
 */
public class BuycarServiceImpl implements BuycarService {
	private BuycarMapper buycarMapper;

	public BuycarMapper getBuycarMapper() {
		return buycarMapper;
	}

	public void setBuycarMapper(BuycarMapper buycarMapper) {
		this.buycarMapper = buycarMapper;
	}

	@Override
	public List<Buycar> selAll() {
		return buycarMapper.selAll();
	}

	@Override
	public List<Buycar> selOneUserCarByID(String id) {
		return buycarMapper.selOneUserCarByID( id );
	}

	@Override
	public int insOne(Buycar buycar) {
		return buycarMapper.insOne( buycar );
	}

	@Override
	public int delOneGoods(String carid) {
		return buycarMapper.delOneGoods( carid );
	}

	@Override
	public int updGoodsnumber(String carid, String goodsnumber) {
		return buycarMapper.updGoodsnumber( carid,goodsnumber );
	}

	@Override
	public Buycar selOneByGoodsidAndUserid(String goodsid, String userid) {
		return buycarMapper.selOneByGoodsidAndUserid( goodsid, userid );
	}

	@Override
	public int addSameGoods(String userid, String goodsid) {
		return buycarMapper.addSameGoods( userid, goodsid );
	}

	@Override
	public int delByUseridAndGoodsid(String userid, int goodsid) {
		return buycarMapper.delByUseridAndGoodsid( userid, goodsid );
	}

	@Override
	public int delByGoodsid(int goodsid) {
		return buycarMapper.delByGoodsid( goodsid );
	}
}
