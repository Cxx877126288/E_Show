package com.gcxy.mapper;

import com.gcxy.pojo.Buyuser;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**扫描包
 * @author HX
 * @title: BuyuserMapper
 * @projectName E_Show
 * @date 2019/7/2  10:23
 */
public interface BuyuserMapper {
	@Select( "select * from buyuser" )
	List<Buyuser> selAll();

	@Select( "select * from buyuser where userid=#{userid} and userpwd=#{userpwd}" )
	List<Buyuser> selOne(Buyuser buyuser);

	@Select( "select * from buyuser where userid=#{userid}" )
	Buyuser selByID(@Param( "userid" )String id);

	@Update( "update buyuser set sex=#{sex},username=#{name},borntime=#{borndate},email=#{email},phone=#{phone},address=#{address},postalcode=#{postalcode}" +
			"where userid=#{userid}" )
	int updUserinfor(@Param( "userid" )String userid,@Param( "sex" )int sex,@Param( "name" )String name,@Param( "phone" )String phone,@Param( "email" )String email,
					 @Param( "address" )String address,@Param( "postalcode" )String postalcode,@Param( "borndate" )Date borndate);

	@Update( "update buyuser set userpwd=#{userpwd} where userid=#{userid}" )
	int updUserpwd(@Param( "userid" )String userid,@Param( "userpwd" )String newpwd);

	@SelectProvider( type = AutoSearch.class,method = "userSearch")
	List<Buyuser> search(UserSearch us);

	@Update( "update buyuser set flag=#{flag} where userid=#{userid}" )
	int updUserFlag(@Param( "userid" )String userid,@Param( "flag" )int flag);

	@Insert( "insert into buyuser values(default,#{userid},#{username},#{userpwd},#{sex},#{borntime},#{email},#{phone},#{address},#{postalcode},#{flag},#{register})" )
	int insNewUser(Buyuser buyuser);
}
