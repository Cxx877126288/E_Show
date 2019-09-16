package com.gcxy.service.impl;

import com.gcxy.mapper.AccountMapper;
import com.gcxy.mapper.AdminSearchAccount;
import com.gcxy.pojo.Account;
import com.gcxy.service.AccountService;

import java.util.List;

/**
 * @author HX
 * @title: AccountServiceImpl
 * @projectName E_Show
 * @date 2019/8/2  12:35
 */
public class AccountServiceImpl implements AccountService {
	private AccountMapper accountMapper ;

	public AccountMapper getAccountMapper() {
		return accountMapper;
	}

	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public List<Account> selByUserid(String userid) {
		return accountMapper.selByUserid( userid );
	}

	@Override
	public List<Account> selByUserid(String userid, int pageNumber, int pageSize) {
		return accountMapper.selByUseridLimit( userid,pageNumber,pageSize );
	}

	@Override
	public Account selOneByID(String accountID) {
		return accountMapper.selOneByID( accountID );
	}

	@Override
	public int insNewAccount(Account account) {
		return accountMapper.insNewAccount( account );
	}

	@Override
	public List<Account> selAll() {
		return accountMapper.selAll();
	}

	@Override
	public List<Account> searchAccount(AdminSearchAccount asa) {
		return accountMapper.searchAccount( asa );
	}

	@Override
	public int updAccountInfor(Account account) {
		return accountMapper.updAccountInfor( account );
	}

	@Override
	public int examineOrder(String adminid, String accountid, int flag, String infor,String examinetime) {
		return accountMapper.examineOrder( adminid, accountid, flag, infor,examinetime);
	}

}
