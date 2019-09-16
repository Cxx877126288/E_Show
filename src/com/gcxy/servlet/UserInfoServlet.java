package com.gcxy.servlet;

import com.gcxy.pojo.Buyuser;
import com.gcxy.pojo.LoginTime;
import com.gcxy.service.BuyuserService;
import com.gcxy.service.LoginTimeService;
import com.gcxy.service.impl.BuyuserServiceImpl;
import com.gcxy.service.impl.LoginTimeServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author HX
 * @title: UserInfoServlet
 * @projectName E_Show
 * @date 2019/8/3  14:07
 * 用户查询自身信息
 */
@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {
	private BuyuserService bs;
	private LoginTimeService lts;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		bs = ac.getBean( "buyuserService",BuyuserServiceImpl.class);
		lts = ac.getBean( "loginTimeService",LoginTimeServiceImpl.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String userid = req.getParameter( "userid" );
		Buyuser buyuser = bs.selByID( userid );
		LoginTime loginTime = lts.selByID( userid );

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

		String borntime = sdf.format( buyuser.getBorntime() );
		String register = sdf2.format( loginTime.getRegistertime() );
		String last = sdf2.format( loginTime.getLastlogintime() );
		if(buyuser.getSex() == 1){
			req.setAttribute( "sex","男" );
		}else if(buyuser.getSex() == 2){
			req.setAttribute( "sex","女" );
		}
		req.setAttribute( "user",buyuser );
		req.setAttribute( "login",loginTime );
		req.setAttribute( "borntime",borntime );
		req.setAttribute( "register",register );
		req.setAttribute( "last",last );
		String flag = req.getParameter( "flag" );
		if(flag.equals( "1" )){
			req.getRequestDispatcher( "/jsp/userinfor.jsp" ).forward( req,resp );
		}
		else if(flag.equals( "2" )){
			req.getRequestDispatcher( "/jsp/upduserinfo.jsp" ).forward( req,resp );
		}else if(flag.equals( "3" )){
			req.setAttribute( "userid",userid );
			req.getRequestDispatcher( "/jsp/upduserpwd.jsp" ).forward( req,resp );
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}
}
