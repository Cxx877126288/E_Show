package com.gcxy.service;

import com.gcxy.pojo.Secondca;

import java.util.List;

/**
 * @author HX
 * @title: SecondacaService
 * @projectName E_Show
 * @date 2019/7/4  14:47
 */
public interface SecondacaService {
	List<Secondca> selByFollowname(String foName);
	List<Secondca> selAll();
	int updFollowName(String newname,int id);
	int updSecondName(String newname,int id);
	List<Secondca> selBySecondName(String name);
	Secondca selByID(int id);
	int insSecondca(String followname,String secondname);
	int delSecondca(int id);
}
