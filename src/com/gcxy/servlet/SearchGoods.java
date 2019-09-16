package com.gcxy.servlet;

import com.gcxy.mapper.myState;
import com.gcxy.pojo.Goods;
import com.gcxy.service.GoodsService;
import com.gcxy.service.impl.GoodsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HX
 * @title: SearchGoods
 * @projectName E_Show
 * @date 2019/7/21  15:16
 * 用户查询商品
 */
@WebServlet("/search")
public class SearchGoods extends HttpServlet {
	private GoodsService gs;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		gs = ac.getBean( "goodsService",GoodsServiceImpl.class );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String category = req.getParameter( "category" );
		String second = req.getParameter( "second" );
		String keyword = req.getParameter( "keyword" );
		String pagenumber = req.getParameter( "pagenumber" );

		int pageStart = 1,pageSize = 9;
		if(category.equals( "null" ))
			category = null;
		if(second.equals( "null" ))
			second = null;
		if(keyword.equals( "null" ))
			keyword = null;
		if(pagenumber != null || !pagenumber.equals( "" )){
			pageStart = Integer.parseInt( pagenumber );
		}
		myState m = new myState( category,second ,keyword );
		List<Goods> search = gs.search( m );
		List<Goods> list = new ArrayList<>(  );
		int count = 0;

		for(int i = (pageSize * (pageStart - 1));i < search.size();i++ ){
			list.add( search.get( i ) );
			count++;
			if(count>= 9 )
				break;
		}
		int listnumber = search.size();
		if(listnumber%9 == 0){
			listnumber = listnumber / 9;
		}else{
			listnumber = listnumber / 9 + 1;
		}

		if(category == null)
			category = "null";
		if(second == null)
			second = "null";
		if(keyword == null)
			keyword = "null";
		if(list.size() == 0)
			list = null;
		req.setAttribute( "category",category );
		req.setAttribute( "second",second );
		req.setAttribute( "keyword",keyword );
		req.setAttribute( "goods",list );
		req.setAttribute( "count",listnumber );
		req.setAttribute( "nowpage",pageStart );
		req.getRequestDispatcher( "/jsp/searchgoods.jsp" ).forward( req,resp );
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}
}
