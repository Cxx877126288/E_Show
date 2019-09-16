package com.gcxy.servlet;

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
import java.util.List;

/**
 * 显示商品信息
 * @author HX
 * @title: ShowGoods
 * @projectName E_Show
 * @date 2019/7/5  15:53
 * 主页面显示商品
 */
@WebServlet("/show")
public class ShowGoods extends HttpServlet {
	private GoodsService goodsService;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		goodsService = ac.getBean("goodsService",GoodsServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "UTF-8" );
		String name = req.getParameter( "secondname" );
		String pagenumber = req.getParameter( "pagenumber" );
		int pageStart = 1;
		int pageSize = 9;
		if(name == null || name.equals( "" )){
			name = "笔记本";
		}
		if(pagenumber != null || !pagenumber.equals( "" )){
			pageStart = Integer.parseInt( pagenumber );
		}
		List<Goods> goods = goodsService.selByPage( pageStart,pageSize,name );
		List<Goods> list = goodsService.selBySecondName( name );
		int count = 0;
		if(!(goods.size() > 0))
			goods = null;
		if(list.size() > 0){
			count = list.size();
			req.setAttribute( "sename",list.get( 0 ).getSecond_name() );
		}
		if(count%9 == 0){
			count = count / 9;
		}else{
			count = count / 9 + 1;
		}
		req.setAttribute( "goods",goods );
		req.setAttribute( "count",count );
		req.setAttribute( "nowpage",pageStart );
		req.getRequestDispatcher( "/jsp/showgoods.jsp" ).forward( req,resp );
			return;
	}
}
