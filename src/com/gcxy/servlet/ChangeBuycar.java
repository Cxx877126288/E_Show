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
 * @title: ChangeBuycar
 * @projectName E_Show
 * @date 2019/7/26  14:44
 * 用户修改购物车某商品的数量  或者  删除某商品
 */
@WebServlet("/updatecar")
public class ChangeBuycar extends HttpServlet {
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
		resp.setCharacterEncoding( "utf-8" );
		req.setCharacterEncoding( "utf-8" );
		String carid = req.getParameter( "carid" );
		String flag = req.getParameter( "flag" );

		if(flag.equals( "1" )){
			if(bs.delOneGoods( carid ) > 0 ){
				resp.getWriter().println( "删除成功" );
			}
			return;
		}else if(flag.equals( "2" )){
			String goodsnumber = req.getParameter( "goodsnumber" );
			if(bs.updGoodsnumber( carid,goodsnumber ) > 0){
				resp.getWriter().println( "修改成功" );
			}
		}
	}


}
