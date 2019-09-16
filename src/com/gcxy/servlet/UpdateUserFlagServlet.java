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
 * @title: UpdateUserFlagServlet
 * @projectName E_Show
 * @date 2019/8/15  23:18
 * 冻结，解冻账户
 */
@WebServlet("/upduserflag")
public class UpdateUserFlagServlet extends HttpServlet {
	private BuyuserService bs;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		bs = ac.getBean( "buyuserService", BuyuserServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String userid = req.getParameter( "userid" );
		String flag = req.getParameter( "flag" );
		int change = 0;
		if(flag != null && !flag.equals( "" ) && !flag.equals( "null" )){
			if(flag.equals( "1" )){
				change = -1;
			}else if(flag.equals( "-1" )){
				change = 1;
			}
		}
		resp.setCharacterEncoding( "utf-8" );
		if(bs.updUserFlag( userid,change ) > 0){
			resp.getWriter().println( "修改成功" );
		}else{
			resp.getWriter().println( "修改失败" );
		}
	}
}
