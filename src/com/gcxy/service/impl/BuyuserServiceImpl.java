package com.gcxy.service.impl;

import com.gcxy.mapper.BuyuserMapper;
import com.gcxy.mapper.UserSearch;
import com.gcxy.pojo.Buyuser;
import com.gcxy.service.BuyuserService;

import java.util.Date;
import java.util.List;

/**
 * @author HX
 * @title: BuyuserServiceImpl
 * @projectName E_Show
 * @date 2019/7/2  10:27
 */
public class BuyuserServiceImpl implements BuyuserService {
	private BuyuserMapper buyuserMapper;

	public BuyuserMapper getBuyuserMapper() {
		return buyuserMapper;
	}

	public void setBuyuserMapper(BuyuserMapper buyuserMapper) {
		this.buyuserMapper = buyuserMapper;
	}

	@Override
	public List<Buyuser> selAll() {
		return buyuserMapper.selAll();
	}

	@Override
	public List<Buyuser> selByID(Buyuser buyuser) {
		List ret = null;
		List get = buyuserMapper.selOne( buyuser );
		if(get.size() > 0 ){
			ret = get;
		}
		return ret;
	}

	@Override
	public Buyuser selByID(String userid) {
		return buyuserMapper.selByID( userid );
	}

	@Override
	public int updUserinfor(String userid, int sex, String name, String phone, String email, String address, String postalcode, Date borndate) {
		return buyuserMapper.updUserinfor( userid, sex, name, phone, email, address, postalcode, borndate );
	}

	@Override
	public int updUserpwd(String userid, String newpwd) {
		return buyuserMapper.updUserpwd( userid, newpwd );
	}

	@Override
	public List<Buyuser> search(UserSearch us) {
		return buyuserMapper.search( us );
	}

	@Override
	public int updUserFlag(String userid, int flag) {
		return buyuserMapper.updUserFlag( userid,flag );
	}

	@Override
	public int insNewUser(Buyuser buyuser) {
		return buyuserMapper.insNewUser( buyuser );
	}
}
