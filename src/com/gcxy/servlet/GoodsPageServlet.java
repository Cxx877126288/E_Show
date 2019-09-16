package com.gcxy.servlet;

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
import java.io.PrintWriter;
import java.util.List;

/**
 * @author HX
 * @title: GoodsPageServlet
 * @projectName E_Show
 * @date 2019/8/8  15:13
 * 管理员 是跳转到新增 还是 商品界面
 */
@WebServlet("/choicegoods")
public class GoodsPageServlet extends HttpServlet {
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

		String goodsid = req.getParameter( "goodsid" );
		List<Category> categories = cs.selAll();
		if(goodsid == null || goodsid.equals( "" )){
			//没有传入商品ID就是新增
			req.setAttribute( "title" ,"新增商品" );

		}else{
			req.setAttribute( "title" , "修改商品信息" );
			Goods goods = gs.selByID( Integer.parseInt( goodsid ) );
			List<Secondca> secondcas = ss.selBySecondName( goods.getSecond_name() );
			String follow_name = secondcas.get( 0 ).getFollow_name();
			List<Secondca> sename = ss.selByFollowname( follow_name );
			req.setAttribute( "sname" ,sename);
			req.setAttribute( "cname",follow_name );
			req.setAttribute( "goods" ,goods);
		}
		req.setAttribute( "cate" ,categories );
		req.getRequestDispatcher( "/jsp/newgoods.jsp" ).forward( req,resp );

	}
}
