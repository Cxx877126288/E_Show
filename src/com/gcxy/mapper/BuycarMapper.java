package com.gcxy.mapper;

import com.gcxy.pojo.Buycar;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author HX
 * @title: BuycarMapper
 * @projectName E_Show
 * @date 2019/7/9  9:20
 */
public interface BuycarMapper {
	@Insert( "insert into buycar values(default,${userid},${goodsid},default)" )
	int insOne(Buycar buycar);

	@Select( "select * from buycar" )
	List<Buycar> selAll();

	@Select( "select * from buycar where userid=#{userid}" )
	List<Buycar> selOneUserCarByID(@Param( "userid" )String id);

	@Delete( "delete from buycar where _id=#{carid}" )
	int delOneGoods(@Param( "carid" )String carid);

	@Update( "update buycar set goodsnumber=#{goodsnumber} where _id=#{carid}" )
	int updGoodsnumber(@Param( "carid" )String carid,@Param( "goodsnumber" )String goodsnumer);

	@Select( "select * from buycar where goodsid=#{goodsid} and userid=#{userid}" )
	Buycar selOneByGoodsidAndUserid(@Param( "goodsid" )String goodsid,@Param( "userid" )String userid);

	@Update( "update buycar set goodsnumber=goodsnumber+1 where goodsid=#{goodsid} and userid=#{userid}" )
	int addSameGoods(@Param( "userid" )String userid,@Param( "goodsid" )String goodsid);

	@Delete( "delete from buycar where userid=#{userid} and goodsid=#{goodsid}" )
	int delByUseridAndGoodsid(@Param( "userid" )String userid,@Param( "goodsid" )int goodsid);

	@Delete( "delete from buycar where goodsid=#{goodsid}" )
	int delByGoodsid(@Param( "goodsid" )int goodsid);
}
