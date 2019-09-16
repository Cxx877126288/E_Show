package com.gcxy.servlet;

import com.gcxy.pojo.Category;
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
import java.util.List;

/**
 * @author HX
 * @title: AdminCategoryServlet
 * @projectName E_Show
 * @date 2019/8/6  14:25
 * 管理员查询所有的类别
 */

@WebServlet("/adcategory")
public class AdminCategoryServlet extends HttpServlet {
	private CategoryService cs;
	private SecondacaService ss;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		cs = ac.getBean( "categoryService", CategoryServiceImpl.class );
		ss = ac.getBean( "secondcaService", SecondacaServiceImpl.class );
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "UTF-8" );
		List<Category> categories = cs.selAll();
		List<Secondca> secondcas = ss.selAll();
		req.setAttribute( "category",categories);
		req.setAttribute( "secondca", secondcas );
		req.getRequestDispatcher( "/jsp/admincategory.jsp" ).forward( req,resp );
	}
}
