package com.gcxy.servlet;

import com.gcxy.pojo.Category;
import com.gcxy.pojo.Goods;
import com.gcxy.pojo.Secondca;
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
import java.io.PrintWriter;
import java.util.List;

/**
 * @author HX
 * @title: UpdateSortServlet
 * @projectName E_Show
 * @date 2019/8/6  16:31
 * 修改类别的信息
 */
@WebServlet("/updsort")
public class UpdateSortServlet extends HttpServlet {
	private CategoryService cs;
	private SecondacaService ss;
	private BuycarService bcs;
	private GoodsService gs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		cs = ac.getBean( "categoryService", CategoryServiceImpl.class );
		ss = ac.getBean( "secondcaService", SecondacaServiceImpl.class );
		bcs = ac.getBean( "buycarService", BuycarServiceImpl.class );
		gs = ac.getBean( "goodsService", GoodsServiceImpl.class );
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String flag = req.getParameter( "flag" );
		resp.setCharacterEncoding( "UTF-8" );
		PrintWriter writer = resp.getWriter();
		if(flag.equals( "1" )){
			String name = req.getParameter( "name" );
			String id = req.getParameter( "id" );
			int _id = Integer.parseInt( id.substring( 1 ) );
			if(id.contains( "c" )){
				Category category = cs.selByID( _id );
				List<Secondca> secondcas = ss.selByFollowname( category.getCommodityname() );
				for(int i = 0;i < secondcas.size();i++){
					ss.updFollowName( name,secondcas.get( i ).get_id() );
				}
					if(cs.updCategoryName( _id,name ) > 0){
						writer.println( "修改成功" );
					}else {
						writer.println( "修改失败" );
					}
			}else {
				Secondca secondca = ss.selByID( _id );
				List<Goods> goods = gs.selBySecondName( secondca.getSecondname() );
				for(int i = 0 ;i < goods.size();i++){
					gs.updGoodsSecondName( name,goods.get( i ).getGoodsid() );
				}
				if(ss.updSecondName( name,_id ) > 0){
					writer.println( "修改成功" );
				}else {
					writer.println( "修改失败" );
				}
			}

		}else if(flag.equals( "2" )){
			Boolean f = false;
			String id = req.getParameter( "id" );
			int _id = Integer.parseInt( id.substring( 1 ) );
			Secondca secondca = ss.selByID( _id );
			List<Goods> goods = gs.selBySecondName( secondca.getSecondname() );
			ss.delSecondca( secondca.get_id() );
			for(int i = 0; i < goods.size();i++){
				int goodsid = goods.get( i ).getGoodsid();
				bcs.delByGoodsid( goodsid );
			}
			for(int i = 0; i < goods.size();i++){
				int goodsid = goods.get( i ).getGoodsid();
				gs.delByGoodsid( goodsid );
			}
			writer.println( "删除成功" );
		}
		writer.close();
	}
}
