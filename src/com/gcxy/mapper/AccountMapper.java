package com.gcxy.mapper;

import com.gcxy.pojo.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author HX
 * @title: AccountMapper
 * @projectName E_Show
 * @date 2019/8/2  12:34
 */
public interface AccountMapper {
	@Select( "select * from accounts where userid=#{userid}" )
	List<Account> selByUserid(@Param( "userid" )String userid);

	@Select( "select * from accounts where userid=#{userid} limit #{index},#{pagesize}" )
	List<Account> selByUseridLimit(@Param( "userid" )String userid,@Param( "index" )int index,@Param( "pagesize" )int pagesize);

	@Insert( "insert into accounts values(#{_id},#{userid},#{goodsid},#{number},#{payway},#{getway},#{address},#{postalcode},#{phone},#{email},#{examineflag},'','','',#{name},#{taketime})" )
	int insNewAccount(Account account);

	@Select( "select * from accounts where _id=#{id}" )
	Account selOneByID(@Param( "id" )String id);

	@Select( "select * from accounts" )
	List<Account> selAll();

	@SelectProvider( type = AutoSearch.class,method = "adminSearchAccount")
	List<Account> searchAccount(AdminSearchAccount asa);

	@Update( "update accounts set payway=#{payway},getway=#{getway},address=#{address},postalcode=#{postalcode},phone=#{phone},email=#{email},name=#{name} where _id=#{_id}" )
	int updAccountInfor(Account account);

	@Update( "update accounts set adminid=#{adminid},examineinfor=#{infor},examineflag=#{flag},examinetime=#{time} where _id=#{accountid}" )
	int examineOrder(@Param( "adminid" )String adminid,@Param( "accountid" )String accountid,@Param( "flag" )int flag,@Param( "infor" )String infor,@Param( "time" )String examinetime);
}
