package com.gcxy.service.impl;

import com.gcxy.mapper.AdminSearch;
import com.gcxy.mapper.GoodsMapper;
import com.gcxy.mapper.myState;
import com.gcxy.pojo.Goods;
import com.gcxy.service.GoodsService;

import java.util.List;

/**
 * @author HX
 * @title: GoodsServiceImpl
 * @projectName E_Show
 * @date 2019/7/5  16:42
 */
public class GoodsServiceImpl implements GoodsService {
	private GoodsMapper goodsMapper;

	public GoodsMapper getGoodsMapper() {
		return goodsMapper;
	}

	public void setGoodsMapper(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}

	@Override
	public List<Goods> selAll() {
		return goodsMapper.selAll();
	}

	@Override
	public List<Goods> selBySecondName(String secondName) {
		return goodsMapper.selBySecondName( secondName );
	}

	@Override
	public List<Goods> search(myState myState) {
		return goodsMapper.search( myState );
	}

	@Override
	public int updGoodsNumber(String goodsid, String number) {
		return goodsMapper.updGoodsNumebr( Integer.parseInt( goodsid ),Integer.parseInt( number ) );
	}

	@Override
	public int updGoodsSecondName(String newname, int id) {
		return goodsMapper.updGoodsSecondName( newname, id );
	}

	@Override
	public int delByGoodsid(int id) {
		return goodsMapper.delByGoodsid( id );
	}

	@Override
	public List<Goods> adminSearch(AdminSearch adminSearch) {
		return goodsMapper.adminSearch( adminSearch );
	}

	@Override
	public int insNewGoods(Goods goods) {
		return goodsMapper.insNewGoods( goods );
	}

	@Override
	public int updOneGoods(Goods goods) {
		return goodsMapper.updOneGoods( goods );
	}

	@Override
	public int examineSucceess(int goodsid, int number) {
		return goodsMapper.examineSucceess( goodsid, number );
	}

	@Override
	public Goods selByID(int id) {
		return goodsMapper.selByID( id );
	}

	@Override
	public List<Goods> selByPage(int pageStart, int pageSize,String name) {
		int start = pageSize * (pageStart - 1);
		return goodsMapper.selByLimit( start,pageSize,name );
	}

}
