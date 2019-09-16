package com.gcxy.service;

import com.gcxy.mapper.AdminSearchAccount;
import com.gcxy.pojo.Account;

import java.util.List;

/**
 * @author HX
 * @title: AccountService
 * @projectName E_Show
 * @date 2019/8/2  12:35
 */
public interface AccountService {
	List<Account> selByUserid(String userid);
	List<Account> selByUserid(String userid,int pageNumber,int pageSize);
	Account selOneByID(String accountID);
	int insNewAccount(Account account);
	List<Account> selAll();
	List<Account> searchAccount(AdminSearchAccount asa);
	int updAccountInfor(Account account);
	int examineOrder(String adminid,String accountid,int flag,String infor,String examinetime);
}
