package com.gcxy.servlet;

import com.gcxy.pojo.Category;
import com.gcxy.pojo.Goods;
import com.gcxy.service.BuycarService;
import com.gcxy.service.CategoryService;
import com.gcxy.service.GoodsService;
import com.gcxy.service.SecondacaService;
import com.gcxy.service.impl.BuycarServiceImpl;
import com.gcxy.service.impl.CategoryServiceImpl;
import com.gcxy.service.impl.GoodsServiceImpl;
import com.gcxy.service.impl.SecondacaServiceImpl;
import org.apache.ibatis.annotations.Insert;
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
 * @title: AddNewCategoryServlet
 * @projectName E_Show
 * @date 2019/8/6  20:05
 * 管理员添加大类
 */
@WebServlet("/addcategory")
public class AddNewCategoryServlet extends HttpServlet {
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
		resp.setCharacterEncoding( "utf-8" );
		PrintWriter writer = resp.getWriter();
		String insname = req.getParameter( "insname" );
		String selid = req.getParameter( "selid" );
		if(selid.equals( "" )){
			if(cs.insCategory( insname ) > 0){
				writer.println( "添加成功" );
			}else{
				writer.println( "添加失败" );
			}
		}else {
			Category category = cs.selByID( Integer.parseInt( selid ) );
			if(ss.insSecondca( category.getCommodityname(),insname ) > 0){
				writer.println( "添加成功" );
			}else{
				writer.println( "添加失败" );
			}
		}
		writer.close();
	}
}
