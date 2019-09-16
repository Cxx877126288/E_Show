package com.gcxy.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author HX
 * @title: AutoSearch
 * @projectName E_Show
 * @date 2019/7/21  15:05
 */
public class AutoSearch {
	public String search(myState myState){
		SQL sql = new SQL().SELECT( "*" );
		sql.FROM( "goods" );

		if(myState.getS1() != null && !myState.getS1().equals( "" ))
			sql.WHERE( "second_name in (select secondname from secondca where follow_name='" + myState.getS1() +"')" );
		if(myState.getS2() != null && !myState.getS2().equals( "" ))
			sql.WHERE( "second_name='"+  myState.getS2() + "'" );
		if(myState.getS3() != null && !myState.getS3().equals( "" ))
			sql.WHERE( "goodsname like" + "'%"+ myState.getS3() + "%'" );

		return sql.toString();
	}

	public String adminSearch(AdminSearch ad){
		SQL sql = new SQL().SELECT( "*" );
		sql.FROM( "goods" );
		if(ad.getCatename() !=null  && !ad.getCatename().equals( "" )){
			sql.WHERE( "second_name in (select secondname from secondca where follow_name='" + ad.getCatename() + "')" );
		}

		if(ad.getSecond_name() != null && !ad.getSecond_name().equals( "" )){
			sql.WHERE( "second_name='" + ad.getSecond_name() + "'" );
		}
		if(ad.getLow_price() > 0.0){
			sql.WHERE( "goodsprice>" + ad.getLow_price() );
		}
		if(ad.getHigh_price() > 0.0){
			sql.WHERE( "goodsprice<" + ad.getHigh_price() );
		}
		if(ad.getKeyword() !=null && !ad.getKeyword().equals( "" )){
			sql.WHERE( "goodsname like " + "'%"+ ad.getKeyword() + "%'" );
		}
		String s = "";
		if(ad.getPagenumber() != 0 && ad.getPagesize() != 0){
			int index = (ad.getPagenumber() - 1) * ad.getPagesize();
			s+=" limit " + index +"," + ad.getPagesize();
		}
		return sql.toString()+ s;
	}
	public String userSearch(UserSearch us){
		SQL sql = new SQL();
		sql.FROM( "buyuser" );
		sql.SELECT( "*" );
		if(us.getUserid() != null && !us.getUserid().equals( "" )){
			sql.WHERE( "userid=" + us.getUserid() );
		}
		if(us.getSex() == 2 || us.getSex() == 1){
			sql.WHERE( "sex=" + us.getSex() );
		}
		if(us.getFlag() == -1 || us.getFlag() == 1){
			sql.WHERE( "flag=" + us.getFlag() );
		}
		return sql.toString() ;
	}
	public String adminSearchAccount(AdminSearchAccount asa){
		SQL sql = new SQL();
		sql.FROM( "accounts" );
		sql.SELECT( "*" );
		if(asa.getUserid() != null && !asa.getUserid().equals( "" ) && !asa.getUserid().equals( "null" )){
			sql.WHERE( "userid=" + asa.getUserid());
		}
		if(asa.getStarttime() != null && !asa.getStarttime().equals( "" ) && !asa.getStarttime().equals( "null" )){
			sql.WHERE( "(taketime>'" + asa.getStarttime() + "%' or taketime like '" + asa.getStarttime() + "%')" );
		}
		if(asa.getEndtime() != null && !asa.getEndtime().equals( "" ) && !asa.getEndtime().equals( "null" )){
			sql.WHERE( "(taketime<'" + asa.getEndtime() + "%' or taketime like '" + asa.getEndtime() + "%')" );
		}
		if(asa.getFlag() != 2){
			sql.WHERE( "examineflag=" + asa.getFlag());
		}
		return sql.toString();
	}
}
