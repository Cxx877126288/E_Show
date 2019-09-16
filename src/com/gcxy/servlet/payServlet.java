package com.gcxy.servlet;

import com.gcxy.pojo.Buycar;
import com.gcxy.pojo.Goods;
import com.gcxy.service.BuycarService;
import com.gcxy.service.GoodsService;
import com.gcxy.service.impl.BuycarServiceImpl;
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
 * @title: payServlet
 * @projectName E_Show
 * @date 2019/7/19  15:05
 * 跳转购物车页面
 */
@WebServlet("/pay")
public class payServlet extends HttpServlet {
	private BuycarService bs;
	private GoodsService gs;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		bs = ac.getBean( "buycarService",BuycarServiceImpl.class );
		gs = ac.getBean( "goodsService",GoodsServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter( "userid" );
		if(userid != null && !userid.equals( "" )){
			List<Buycar> cars = bs.selOneUserCarByID( userid );
			List<Goods> goods = new ArrayList<>(  );
			for(int i = 0;i < cars.size();i++){
				Goods good = gs.selByID( cars.get( i ).getGoodsid() );
				goods.add( good );
			}
			req.setAttribute( "cars",cars );
			req.setAttribute( "goods",goods );
			req.setAttribute( "userid",userid );
			//req.setAttribute( "goodslength",goods.size() );
			req.getRequestDispatcher( "/jsp/paypage.jsp" ).forward( req,resp );
		}

	}
}
