package com.gcxy.servlet;

import com.gcxy.pojo.Buycar;
import com.gcxy.service.BuycarService;
import com.gcxy.service.impl.BuycarServiceImpl;
import com.gcxy.service.impl.BuyuserServiceImpl;
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
 * 添加购物车
 * @author HX
 * @title: AddBuycar
 * @projectName E_Show
 * @date 2019/7/9  9:34
 * 往购物车添加商品
 */
@WebServlet("/insBuycar")
public class AddBuycar extends HttpServlet {
	private BuycarService bs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		bs = ac.getBean( "buycarService",BuycarServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter( "userid" );
		String goodsid = req.getParameter( "goodsid" );
		//查看该用户的购物车 是否拥有同一样东西
		Buycar buycar = new Buycar();
		buycar.setUserid( userid );
		buycar.setGoodsid( Integer.parseInt( goodsid ) );
		Buycar checkCar = bs.selOneByGoodsidAndUserid( goodsid, userid );
		int result = 0;
		if(checkCar != null)	{
			//拥有相同的东西  数量相加1
			result = bs.addSameGoods( buycar.getUserid(),String.valueOf( buycar.getGoodsid() ) );
		}
		else{
			//否则新增一条记录
			result = bs.insOne( buycar );
		}

		resp.setCharacterEncoding( "UTF-8" );
		PrintWriter out = resp.getWriter();
		if( result == 1){
			out.println( "添加购物车成功");
		}else{
			out.println( "添加购物车失败");
		}

		out.close();

		return;
	}
}
