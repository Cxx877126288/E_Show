package com.gcxy.service;

import com.gcxy.pojo.Category;

import java.util.List;

/**
 * @author HX
 * @title: CategoryService
 * @projectName E_Show
 * @date 2019/7/4  14:09
 */
public interface CategoryService {
	List<Category> selAll();
	int updCategoryName(int id,String name);
	Category selByID(int id);
	int insCategory(String name);
}
