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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HX
 * @title: RegisterUserServlet
 * @projectName E_Show
 * @date 2019/8/23  19:52
 * 注册新用户
 */
@WebServlet("/insuser")
public class RegisterUserServlet extends HttpServlet {
	private BuyuserService bs;
	private LoginTimeService lts;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext( getServletContext() );
		bs = ac.getBean( "buyuserService", BuyuserServiceImpl.class );
		lts = ac.getBean( "loginTimeService",LoginTimeServiceImpl.class);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String userid = req.getParameter( "userid" );
		String username = req.getParameter( "username" );
		String userpwd = req.getParameter( "userpwd" );
		String borntime = req.getParameter( "borntime" );
		String email = req.getParameter( "email" );
		String address = req.getParameter( "address" );
		String phone = req.getParameter( "phone" );
		String postalcode = req.getParameter( "postalcode" );
		String sex = req.getParameter( "sex" );

		Buyuser buyuser = new Buyuser();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd" );
		buyuser.setUserid( userid );
		buyuser.setUserpwd( userpwd );
		buyuser.setSex( Integer.parseInt( sex ) );
		buyuser.setAddress( address );
		buyuser.setUsername( username );
		try {
			buyuser.setBorntime( sdf2.parse( borntime ) );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		buyuser.setEmail( email );
		buyuser.setPhone( phone );
		buyuser.setPostalcode( Integer.parseInt( postalcode ) );
		buyuser.setFlag( 1 );
		String date = sdf.format( new Date(  ) );
		buyuser.setRegister( date );
		resp.setCharacterEncoding( "utf-8" );
		if(bs.insNewUser( buyuser ) > 0){
			LoginTime lt = new LoginTime();
			lt.setUserid( userid );
			try {
				lt.setRegistertime( sdf.parse( date ) );
				lt.setLastlogintime( sdf.parse( "0000-00-00 00:00:00" ) );
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(lts.insNewUser( lt ) > 0){
				resp.getWriter().println( "注册成功" );
			}else {
				resp.getWriter().println( "注册失败" );
			}
		}else{
			resp.getWriter().println( "注册失败" );
		}


	}
}
