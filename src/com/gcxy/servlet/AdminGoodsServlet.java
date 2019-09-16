package com.gcxy.servlet;

import com.gcxy.mapper.AdminSearch;
import com.gcxy.mapper.AutoSearch;
import com.gcxy.pojo.Category;
import com.gcxy.pojo.Goods;
import com.gcxy.pojo.Secondca;
import com.gcxy.service.CategoryService;
import com.gcxy.service.GoodsService;
import com.gcxy.service.SecondacaService;
import com.gcxy.service.impl.CategoryServiceImpl;
import com.gcxy.service.impl.GoodsServiceImpl;
import com.gcxy.service.impl.SecondacaServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HX
 * @title: AdminGoodsServlet
 * @projectName E_Show
 * @date 2019/8/7  15:02
 * 管理员查询商品列表
 */
@WebServlet("/goods")
public class AdminGoodsServlet extends HttpServlet {
	private CategoryService cs;
	private SecondacaService ss;
	private GoodsService gs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		cs = ac.getBean( "categoryService", CategoryServiceImpl.class );
		ss = ac.getBean( "secondcaService", SecondacaServiceImpl.class );
		gs = ac.getBean( "goodsService", GoodsServiceImpl.class );
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		int pagesize = 5;
		AdminSearch ad = new AdminSearch();
		String secondname = req.getParameter( "secondname" );
		String lowpirce = req.getParameter( "lowpirce" );
		String highprice = req.getParameter( "highprice" );
		String keyword = req.getParameter( "keyword" );
		String pagenumber = req.getParameter( "pagenumber" );
		int number = 1;
		String catename = req.getParameter( "catename" );

		if(secondname == null || secondname.equals( "" ) || secondname.equals( "null" )){
			ad.setSecond_name( null );
		}else{
			ad.setSecond_name( secondname );
		}
		if(catename == null || catename.equals( "" ) || catename.equals( "null" )){}
		else{
			ad.setCatename( catename );
		}
		if(lowpirce == null || lowpirce.equals( "" ) || lowpirce.equals( "null" )){}
		else{
			ad.setLow_price( Double.parseDouble( lowpirce ) );
		}
		if(highprice == null || highprice.equals( "" ) || highprice.equals( "null" )){}
		else{
			ad.setHigh_price( Double.parseDouble( highprice ) );
		}

		if(keyword == null || keyword.equals( "" ) || keyword.equals( "null" )){}
		else{
			ad.setKeyword(keyword );
		}
		List<Goods> getGoodsCount = gs.adminSearch( ad );
		int count = getGoodsCount.size();

		if(pagenumber != null && pagenumber.equals( "" )){
			number = Integer.parseInt( pagenumber );
		}
		ad.setPagenumber( number );
		ad.setPagesize( pagesize );
		List<Goods> goods = gs.adminSearch( ad );
		List<Category> categories = cs.selAll();
		List<Secondca> secondcas = ss.selByFollowname( catename );

		req.setAttribute( "cate" ,catename);
		req.setAttribute( "seco" , secondname );
		req.setAttribute( "keyword",keyword );
		req.setAttribute( "lowprice",lowpirce );
		req.setAttribute( "highprice",highprice );
		req.setAttribute( "secolist" , secondcas );

		req.setAttribute( "pagenumber",number );
		req.setAttribute( "count",count );
		req.setAttribute( "goods",goods );
		req.setAttribute( "cates" , categories );
		req.getRequestDispatcher( "/jsp/admingoods.jsp" ).forward( req,resp );
	}
}
