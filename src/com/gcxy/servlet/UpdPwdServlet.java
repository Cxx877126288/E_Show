package com.gcxy.servlet;

import com.gcxy.service.BuyuserService;
import com.gcxy.service.impl.BuyuserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HX
 * @title: UpdPwdServlet
 * @projectName E_Show
 * @date 2019/8/3  16:40
 * 用户修改密码
 */
@WebServlet("/updpwd")
public class UpdPwdServlet extends HttpServlet {
	private BuyuserService bs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		bs = ac.getBean( "buyuserService", BuyuserServiceImpl.class );
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req,resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String userid = req.getParameter( "userid" );
		String newpwd = req.getParameter( "newpwd" );
		if(bs.updUserpwd( userid,newpwd ) > 0){
			resp.setCharacterEncoding( "utf-8" );
			resp.getWriter().println( "修改成功，请重新登录" );
			resp.getWriter().close();
		}
	}
}
