package com.gcxy.mapper;

import com.gcxy.pojo.LoginTime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author HX
 * @title: LoginTimeMapper
 * @projectName E_Show
 * @date 2019/8/1  12:38
 */
public interface LoginTimeMapper {
	@Select( "select * from logintime where userid=#{userid}" )
	LoginTime selByID(@Param( "userid" ) String id);

	@Update( "update logintime set lastlogintime=#{datetime} where userid=#{userid}" )
	int updLoginTime(@Param( "userid" )String userid,@Param( "datetime" )Date datetime);

	@Update( "update logintime set logincount=logincount+1 where userid=#{userid}" )
	int updLoginCount(@Param( "userid" )String userid);

	@Insert( "insert into logintime values(#{userid},#{registertime},#{lastlogintime},#{logincount})" )
	int insNewUser(LoginTime loginTime);
}
