package com.gcxy.service;

import com.gcxy.pojo.LoginTime;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author HX
 * @title: LoginTimeService
 * @projectName E_Show
 * @date 2019/8/1  12:38
 */
public interface LoginTimeService {
	LoginTime selByID(String userid);

	int updLoginTime(String userid,Date datetime);

	int updLoginCount(String userid);

	int insNewUser(LoginTime loginTime);
}
