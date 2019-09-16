package com.gcxy.servlet;

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
import java.io.PrintWriter;

/**
 * @author HX
 * @title: DeleteGoodsServlet
 * @projectName E_Show
 * @date 2019/8/8  17:04
 * 管理员删除某个商品
 */
@WebServlet("/delgoods")
public class DeleteGoodsServlet extends HttpServlet {
	private BuycarService bs;
	private GoodsService gs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		gs = ac.getBean( "goodsService", GoodsServiceImpl.class );
		bs = ac.getBean( "buycarService",BuycarServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		resp.setCharacterEncoding( "utf-8" );
		PrintWriter writer = resp.getWriter();

		String goodsid = req.getParameter( "goodsid" );
		if(goodsid != null && !goodsid.equals( "" )){
			if(	gs.delByGoodsid( Integer.parseInt( goodsid ) ) > 0){
				bs.delByGoodsid( Integer.parseInt( goodsid ) );
				writer.println( "删除成功" );
			}else {
				writer.println( "删除失败" );
			}
		}

		writer.close();

	}
}
