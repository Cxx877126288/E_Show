package com.gcxy.servlet;


import com.gcxy.pojo.Buycar;
import com.gcxy.pojo.Buyuser;
import com.gcxy.pojo.Goods;
import com.gcxy.service.*;
import com.gcxy.service.impl.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author HX
 * @title: MainPage
 * @projectName E_Show
 * @date 2019/7/2  10:29
 * 用户登陆  以及  主界面的初始化
 */
@WebServlet("/main")
public class MainPage extends HttpServlet {
	private BuyuserService bs;
	private CategoryService cs;
	private SecondacaService ss;
	private BuycarService bcs;
	private GoodsService gs;
	private LoginTimeService lts;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		bs = ac.getBean( "buyuserService", BuyuserServiceImpl.class );
		cs = ac.getBean( "categoryService", CategoryServiceImpl.class );
		ss = ac.getBean( "secondcaService", SecondacaServiceImpl.class );
		bcs = ac.getBean( "buycarService", BuycarServiceImpl.class );
		gs = ac.getBean( "goodsService", GoodsServiceImpl.class );
		lts = ac.getBean( "loginTimeService", LoginTimeServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter( "userid" );
		String userpwd = req.getParameter( "userpwd" );
		if (userid == null && userpwd == null) {
			req.setCharacterEncoding( "UTF-8" );
			req.setAttribute( "userid", null );
			req.setAttribute( "name", null );
			req.setAttribute( "goodsnumber", null );
			req.setAttribute( "tprice", null );
			req.setAttribute( "category", cs.selAll() );
			req.setAttribute( "secondca", ss.selAll() );
			req.getRequestDispatcher( "/jsp/main.jsp" ).forward( req, resp );
			return;
		}
		String inCode = req.getParameter( "code" );
		String code = req.getSession().getAttribute( "code" ).toString();
		if (inCode != null) {
			if (inCode.equals( code )) {
				Buyuser buyuser = new Buyuser();
				buyuser.setUserid( userid );
				buyuser.setUserpwd( userpwd );
				List<Buyuser> one = bs.selByID( buyuser );
				if (one != null) {
					if (one.get( 0 ).getFlag() == -1) {
						resp.sendRedirect( "/jsp/login.jsp?userid=" + userid + "&msg=show" );
					} else {
						String flag = req.getParameter( "flag" );
						if (flag.equals( "1" )) {
							SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
							String nowDate = sdf.format( new Date() );
							Date date = null;
							try {
								date = sdf.parse( nowDate );
							} catch (ParseException e) {
								e.printStackTrace();
							}
							lts.updLoginTime( userid, date );
							lts.updLoginCount( userid );
						}
						int goodsnumber = 0;
						String name = one.get( 0 ).getUsername();
						List<Buycar> buycars = bcs.selOneUserCarByID( userid );
						double total_price = 0.0;
						for (int i = 0; i < buycars.size(); i++) {
							double price = gs.selByID( buycars.get( i ).getGoodsid() ).getGoodsprice();
							double number = buycars.get( i ).getGoodsnumber();
							total_price += (price * number);
							goodsnumber += (int) number;
						}
						total_price = (double) Math.round( total_price * 100 ) / 100;
						req.setCharacterEncoding( "UTF-8" );
						req.setAttribute( "userid", buyuser.getUserid() );
						req.setAttribute( "name", name );
						req.setAttribute( "goodsnumber", goodsnumber );
						req.setAttribute( "tprice", total_price );
						req.setAttribute( "category", cs.selAll() );
						req.setAttribute( "secondca", ss.selAll() );
						req.getRequestDispatcher( "/jsp/main.jsp" ).forward( req, resp );
					}

				} else {
					resp.setCharacterEncoding( "UTF-8" );
					resp.sendRedirect( req.getContextPath() + "/jsp/login.jsp?userid=" + userid + "&msg=false" );
				}

			} else {
				resp.setCharacterEncoding( "UTF-8" );
				resp.sendRedirect( req.getContextPath() + "/jsp/login.jsp?userid=" + userid + "&msg=yan" );
			}
		} else {
			Buyuser buyuser = new Buyuser();
			buyuser.setUserid( userid );
			buyuser.setUserpwd( userpwd );
			List<Buyuser> one = bs.selByID( buyuser );
			if (one != null) {
				if (one.get( 0 ).getFlag() == -1) {
					resp.sendRedirect( "/jsp/login.jsp?userid=" + userid + "&msg=show" );
				} else {
					String flag = req.getParameter( "flag" );
					if (flag.equals( "1" )) {
						SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
						String nowDate = sdf.format( new Date() );
						Date date = null;
						try {
							date = sdf.parse( nowDate );
						} catch (ParseException e) {
							e.printStackTrace();
						}
						lts.updLoginTime( userid, date );
						lts.updLoginCount( userid );
					}
					int goodsnumber = 0;
					String name = one.get( 0 ).getUsername();
					List<Buycar> buycars = bcs.selOneUserCarByID( userid );
					double total_price = 0.0;
					for (int i = 0; i < buycars.size(); i++) {
						double price = gs.selByID( buycars.get( i ).getGoodsid() ).getGoodsprice();
						double number = buycars.get( i ).getGoodsnumber();
						total_price += (price * number);
						goodsnumber += (int) number;
					}
					total_price = (double) Math.round( total_price * 100 ) / 100;
					req.setCharacterEncoding( "UTF-8" );
					req.setAttribute( "userid", buyuser.getUserid() );
					req.setAttribute( "name", name );
					req.setAttribute( "goodsnumber", goodsnumber );
					req.setAttribute( "tprice", total_price );
					req.setAttribute( "category", cs.selAll() );
					req.setAttribute( "secondca", ss.selAll() );
					req.getRequestDispatcher( "/jsp/main.jsp" ).forward( req, resp );
				}

			} else {
				resp.setCharacterEncoding( "UTF-8" );
				resp.sendRedirect( req.getContextPath() + "/jsp/login.jsp?userid=" + userid + "&msg=false" );
			}
		}
	}
}


