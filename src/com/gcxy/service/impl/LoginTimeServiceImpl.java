package com.gcxy.service.impl;

import com.gcxy.mapper.LoginTimeMapper;
import com.gcxy.pojo.LoginTime;
import com.gcxy.service.LoginTimeService;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author HX
 * @title: LoginTimeServiceImpl
 * @projectName E_Show
 * @date 2019/8/1  12:38
 */
public class LoginTimeServiceImpl implements LoginTimeService {
	private LoginTimeMapper loginTimeMapper;

	public LoginTimeMapper getLoginTimeMapper() {
		return loginTimeMapper;
	}

	public void setLoginTimeMapper(LoginTimeMapper loginTimeMapper) {
		this.loginTimeMapper = loginTimeMapper;
	}

	@Override
	public LoginTime selByID(String userid) {
		return loginTimeMapper.selByID( userid );
	}

	@Override
	public int updLoginTime(String userid, Date datetime) {
		return loginTimeMapper.updLoginTime( userid, datetime );
	}

	@Override
	public int updLoginCount(String userid) {
		return loginTimeMapper.updLoginCount( userid );
	}

	@Override
	public int insNewUser(LoginTime loginTime) {
		return loginTimeMapper.insNewUser( loginTime );
	}
}
