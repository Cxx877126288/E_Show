package com.gcxy.servlet;

import com.gcxy.pojo.Buycar;
import com.gcxy.pojo.Buyuser;
import com.gcxy.pojo.Goods;
import com.gcxy.service.BuycarService;
import com.gcxy.service.BuyuserService;
import com.gcxy.service.GoodsService;
import com.gcxy.service.LoginTimeService;
import com.gcxy.service.impl.BuycarServiceImpl;
import com.gcxy.service.impl.BuyuserServiceImpl;
import com.gcxy.service.impl.GoodsServiceImpl;
import com.gcxy.service.impl.LoginTimeServiceImpl;
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
 * @title: OrderServlet
 * @projectName E_Show
 * @date 2019/8/1  13:37
 * 选择的购物车的信息
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private BuyuserService bs;
	private BuycarService bcs;
	private LoginTimeService lts;
	private GoodsService gs;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		gs = ac.getBean("goodsService",GoodsServiceImpl.class );
		lts = ac.getBean( "loginTimeService",LoginTimeServiceImpl.class );
		bcs = ac.getBean( "buycarService",BuycarServiceImpl.class );
		bs = ac.getBean( "buyuserService",BuyuserServiceImpl.class );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String userid = req.getParameter( "userid" );
		String goodsid = req.getParameter( "goodsid" );
		String[] ids = goodsid.split( "," );

		List<Buycar> cars = new ArrayList<>(  );
		List<Goods> goods = new ArrayList<>(  );
		for(int i = 0; i < ids.length;i++){
			goods.add( gs.selByID( Integer.parseInt( ids[i] ) ) );
			cars.add( bcs.selOneByGoodsidAndUserid( ids[i],userid ) );
		}

		double totalPrice = 0.0;
		int count = 0;
		String perNumber = "";
		for(int i = 0;i < cars.size();i++) {
			double goodsprice = goods.get( i ).getGoodsprice();
			double goodsnumber = cars.get( i ).getGoodsnumber();
			if (i < cars.size() -1){
				perNumber += String.valueOf( (int) goodsnumber ) + ',';
			}else{
				perNumber += String.valueOf( (int)goodsnumber );
			}
			count += goodsnumber;
			totalPrice += goodsnumber * goodsprice;
		}
		Buyuser buyuser = bs.selByID( userid );

		req.setAttribute( "pernumber",perNumber );
		req.setAttribute( "userid",userid );
		req.setAttribute( "goodsid",goodsid );
		req.setAttribute( "category",String.valueOf( ids.length ) );
		req.setAttribute( "number",String.valueOf( count ) );
		req.setAttribute( "price",String.valueOf( totalPrice ) );
		req.setAttribute( "user",buyuser );

		req.getRequestDispatcher( "/jsp/settlement.jsp" ).forward( req,resp );
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req,resp );
	}
}
