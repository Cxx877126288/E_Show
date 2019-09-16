package com.gcxy.service.impl;

import com.gcxy.mapper.CategoryMapper;
import com.gcxy.pojo.Category;
import com.gcxy.service.CategoryService;

import java.util.List;

/**
 * @author HX
 * @title: CategoryServiceImpl
 * @projectName E_Show
 * @date 2019/7/4  14:10
 */
public class CategoryServiceImpl implements CategoryService {
	private CategoryMapper categoryMapper;

	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}

	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<Category> selAll() {
		return categoryMapper.selAll();
	}

	@Override
	public int updCategoryName(int id, String name) {
		return categoryMapper.updCategoryName( id, name );
	}

	@Override
	public Category selByID(int id) {
		return categoryMapper.selByID( id );
	}

	@Override
	public int insCategory(String name) {
		return categoryMapper.insCategory( name );
	}
}
