package com.gcxy.mapper;

import com.gcxy.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author HX
 * @title: GoodsMapper
 * @projectName E_Show
 * @date 2019/7/5  16:36
 */
public interface GoodsMapper {
	@Select( "select * from goods order by goodsid ASC" )
	List<Goods> selAll();

	@Select( "select * from goods where second_name=#{secondname}" )
	List<Goods> selBySecondName(@Param( "secondname" ) String secondname);

	@Select( "select * from goods where goodsid=#{goodsid}" )
	Goods selByID(@Param( "goodsid" )int id);

	@Select( "select * from goods where second_name=#{name} limit #{start},#{size} " )
	List<Goods> selByLimit(@Param( "start" )int start,@Param( "size" )int size,@Param( "name" )String name);

	@SelectProvider( type = AutoSearch.class,method = "search")
	List<Goods> search(myState myState);

	@SelectProvider( type = AutoSearch.class,method = "adminSearch")
	List<Goods> adminSearch(AdminSearch adminSearch);

	@Update( "update goods set goodsnumber=goodsnumber-#{number} where goodsid=#{goodsid}" )
	int updGoodsNumebr(@Param( "goodsid" )int goodsid,@Param( "number" )int number);

	@Update( "update goods set second_name=#{newname} where goodsid=#{id}" )
	int updGoodsSecondName(@Param( "newname" )String newname,@Param( "id" )int id);

	@Delete( "delete from goods where goodsid=#{goodsid}" )
	int delByGoodsid(@Param( "goodsid" )int id);

	@Insert( "insert into goods values(#{goodsid},#{second_name},#{goodsname},#{goodsprice},#{goodsnumber},#{goodsinfor},#{goodsimage})" )
	int insNewGoods(Goods goods);

	@Update( "update goods set second_name=#{second_name},goodsname=#{goodsname},goodsprice=#{goodsprice}" +
			",goodsnumber=#{goodsnumber},goodsinfor=#{goodsinfor},goodsimage=#{goodsimage} where goodsid=#{goodsid}")
	int updOneGoods(Goods goods);

	@Update( "update goods set goodsnumber=goodsnumber-#{number} where goodsid=#{goodsid}" )
	int examineSucceess(@Param( "goodsid" )int goodsid,@Param( "number" )int number);

}
