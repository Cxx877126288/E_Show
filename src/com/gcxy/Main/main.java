package com.gcxy.Main;

import com.gcxy.Utils.XmlOpreation;
import com.gcxy.mapper.*;
import com.gcxy.pojo.*;
import com.gcxy.service.*;
import com.gcxy.service.impl.*;
import net.sf.json.JSONArray;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author HX
 * @title: main
 * @projectName E_Show
 * @date 2019/7/2  10:43
 */
public class main {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		BuyuserService bs = ac.getBean( "buyuserService",BuyuserServiceImpl.class );
		CategoryService cs = ac.getBean( "categoryService",CategoryServiceImpl.class );
		SecondacaService ss = ac.getBean( "secondcaService",SecondacaServiceImpl.class );
		GoodsService gs = ac.getBean("goodsService",GoodsServiceImpl.class );
		BuycarService bcs = ac.getBean( "buycarService",BuycarServiceImpl.class );
		LoginTimeService lts = ac.getBean( "loginTimeService",LoginTimeServiceImpl.class );
		AccountService as = ac.getBean( "accountService",AccountServiceImpl.class);

		System.out.println(gs.selAll().size());
	}
}
