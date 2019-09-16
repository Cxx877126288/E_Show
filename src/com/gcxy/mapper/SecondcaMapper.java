package com.gcxy.mapper;

import com.gcxy.pojo.Secondca;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author HX
 * @title: SecondcaMapper
 * @projectName E_Show
 * @date 2019/7/4  14:46
 */
public interface SecondcaMapper {
	@Select( "select * from secondca where follow_name=#{foName}" )
	List<Secondca> selByFollowname(@Param( "foName" ) String foName);

	@Select( "select * from secondca" )
	List<Secondca> selAll();

	@Select( "select * from secondca where secondname=#{name}" )
	List<Secondca> selBySecondName(@Param( "name" ) String name);

	@Update( "update secondca set follow_name=#{newname} where _id=#{id}" )
	int updFollowName(@Param( "newname" )String newname,@Param( "id" )int id);

	@Update( "update secondca set secondname=#{newname} where _id=#{id}" )
	int updSecondName(@Param( "newname" )String newname,@Param( "id" )int id);

	@Select( "select * from secondca where _id=#{id}" )
	Secondca selByID(@Param( "id" ) int id);

	@Insert( "insert into secondca values(default,#{followname},#{secondname})" )
	int insSecondca(@Param( "followname" ) String followname,@Param( "secondname" ) String secondname);

	@Delete( "delete from secondca where _id=#{id}" )
	int delSecondca(@Param( "id" ) int id);
}
