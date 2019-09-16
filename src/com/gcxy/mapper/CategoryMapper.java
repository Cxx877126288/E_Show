package com.gcxy.mapper;

import com.gcxy.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author HX
 * @title: CategoryMapper
 * @projectName E_Show
 * @date 2019/7/4  14:07
 */

public interface CategoryMapper {
	@Select( "select * from category order by _id" )
	List<Category> selAll();

	@Update( "update category set commodityname=#{name} where _id=#{id}" )
	int updCategoryName(@Param( "id" )int id,@Param( "name" )String name);

	@Select( "select * from category where _id=#{id}" )
	Category selByID(@Param( "id" )int id);

	@Insert( "insert into category values(default,#{name})" )
	int insCategory(@Param( "name" ) String name);
}
