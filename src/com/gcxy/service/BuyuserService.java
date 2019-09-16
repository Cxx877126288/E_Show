package com.gcxy.service;

import com.gcxy.mapper.UserSearch;
import com.gcxy.pojo.Buyuser;

import java.util.Date;
import java.util.List;

/**
 * @author HX
 * @title: BuyuserService
 * @projectName E_Show
 * @date 2019/7/2  10:26
 */
public interface BuyuserService {
	List<Buyuser> selAll();
	List<Buyuser> selByID(Buyuser buyuser);
	Buyuser selByID(String userid);
	int updUserinfor(String userid,int sex,String name,String phone,String email,String address,String postalcode,Date borndate);
	int updUserpwd(String userid,String newpwd);
	List<Buyuser> search(UserSearch us);
	int updUserFlag(String userid,int flag);
	int insNewUser(Buyuser buyuser);
}
