package com.gcxy.service.impl;

import com.gcxy.mapper.SecondcaMapper;
import com.gcxy.pojo.Secondca;
import com.gcxy.service.SecondacaService;

import java.util.List;

/**
 * @author HX
 * @title: SecondacaServiceImpl
 * @projectName E_Show
 * @date 2019/7/4  14:47
 */
public class SecondacaServiceImpl implements SecondacaService {
	private SecondcaMapper secondcaMapper;

	public SecondcaMapper getSecondcaMapper() {
		return secondcaMapper;
	}

	public void setSecondcaMapper(SecondcaMapper secondcaMapper) {
		this.secondcaMapper = secondcaMapper;
	}

	@Override
	public List<Secondca> selByFollowname(String foName) {
		return secondcaMapper.selByFollowname( foName );
	}

	@Override
	public List<Secondca> selAll() {
		return secondcaMapper.selAll();
	}

	@Override
	public int updFollowName(String newname, int id) {
		return secondcaMapper.updFollowName( newname, id );
	}

	@Override
	public int updSecondName(String newname, int id) {
		return secondcaMapper.updSecondName( newname, id );
	}

	@Override
	public List<Secondca> selBySecondName(String name) {
		return secondcaMapper.selBySecondName( name );
	}

	@Override
	public Secondca selByID(int id) {
		return secondcaMapper.selByID( id );
	}

	@Override
	public int insSecondca(String followname, String secondname) {
		return secondcaMapper.insSecondca( followname,secondname );
	}

	@Override
	public int delSecondca(int id) {
		return secondcaMapper.delSecondca( id );
	}
}
