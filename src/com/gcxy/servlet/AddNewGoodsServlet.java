package com.gcxy.servlet;

import com.gcxy.Utils.FileOp;
import com.gcxy.pojo.Goods;
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
import java.io.*;
import java.util.List;

/**
 * @author HX
 * @title: AddNewGoodsServlet
 * @projectName E_Show
 * @date 2019/8/9  13:32
 * 管理员添加或修改新的商品
 */
@WebServlet("/addgoods")
public class AddNewGoodsServlet extends HttpServlet {
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service( req,resp );
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req,resp );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		resp.setCharacterEncoding( "utf-8" );
		PrintWriter writer = resp.getWriter();
		String flag = req.getParameter( "flag" );

		if(flag != null && !flag.equals( "" )){
			String goodsname = req.getParameter( "goodsname" );
			String secondname = req.getParameter( "secondname" );
			String goodsprice = req.getParameter( "goodsprice" );
			String image = req.getParameter( "image" );
			String goodsinfor = req.getParameter( "goodsinfor" );
			String goodsnumber = req.getParameter( "goodsnumber" );
			String filePath=req.getSession().getServletContext().getRealPath( "" );
			String newPath = "";
			String[] split = filePath.split( "\\\\" );
			for(int i = 0 ; i < split.length - 3;i++){
				newPath += split[i] + "\\";
			}
			String imageName = image.split( "\\\\" )[image.split( "\\\\" ).length - 1];
			newPath +=  "web\\images\\" + imageName;
			if(!FileOp.FileExist( newPath )){
				FileOp.ImageWrite( image,newPath );
			}
			image = "images/" + imageName;

			Goods goods = new Goods();
			goods.setGoodsname( goodsname );
			goods.setSecond_name( secondname );
			goods.setGoodsprice( Double.parseDouble( goodsprice ) );
			goods.setGoodsimage( image );
			goods.setGoodsinfor( goodsinfor );

			goods.setGoodsnumber( Integer.parseInt( goodsnumber.trim() ) );
			if(flag.equals( "1" )){
				List<Goods> all = gs.selAll();
				Goods count = null;
				if(all.size() > 0){
					count =  all.get( all.size() - 1  );
					goods.setGoodsid( count.getGoodsid() + 1 );
				}else {
					goods.setGoodsid( 1 );
				}


				if(gs.insNewGoods( goods ) > 0){
					writer.println( "新增商品成功" );
				}else {
					writer.println( "新增商品失败" );
				}
			}else if(flag.equals( "2" )){
				String goodsid = req.getParameter( "goodsid" );
				goods.setGoodsid( Integer.parseInt( goodsid.trim() ) );

				if(gs.updOneGoods( goods ) > 0){
					writer.println( "修改商品成功" );
				}else {
					writer.println( "修改商品失败" );
				}
			}
			writer.close();
		}
	}
}
