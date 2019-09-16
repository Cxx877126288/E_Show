package com.gcxy.servlet;

import com.gcxy.pojo.Goods;
import com.gcxy.service.BuyuserService;
import com.gcxy.service.GoodsService;
import com.gcxy.service.impl.BuyuserServiceImpl;
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
 * @title: Detailed
 * @projectName E_Show
 * @date 2019/7/9  15:11
 * 查看商品的详情
 */
@WebServlet("/detailed")
public class Detailed extends HttpServlet {
	private GoodsService gs;
	private BuyuserService bs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		gs = ac.getBean( "goodsService",GoodsServiceImpl.class );
		bs = ac.getBean( "buyuserService",BuyuserServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String goodsid = req.getParameter( "goodsid" );
		String userid = req.getParameter( "userid" );
		if(!userid.equals( "-1" )){
			req.setAttribute( "userpwd",bs.selByID( userid ).getUserpwd() );
		}
		//goods.add();
		req.setAttribute( "goods", gs.selByID( Integer.parseInt( goodsid ) ));

		req.getRequestDispatcher( "/jsp/detailed.jsp" ).forward( req,resp );
	}
}
