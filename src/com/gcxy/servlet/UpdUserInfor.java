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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HX
 * @title: UpdUserInfor
 * @projectName E_Show
 * @date 2019/8/3  15:11
 * 用户修改信息
 */
@WebServlet("/upduserinfor")
public class UpdUserInfor extends HttpServlet {
	private BuyuserService bs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		bs = ac.getBean( "buyuserService",BuyuserServiceImpl.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req,resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String userid = req.getParameter( "userid" );
		String sex = req.getParameter( "sex" );
		String name = req.getParameter( "name" );
		String borntime = req.getParameter( "borntime" );
		String email = req.getParameter( "email" );
		String phone = req.getParameter( "phone" );
		String address = req.getParameter( "address" );
		String postalcode = req.getParameter( "postalcode" );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = null;
		int changeSex = -1;
		try {
			date = sdf.parse( borntime );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(sex.equals( "男" ))
			changeSex = 1;
		else if(sex.equals( "女" ))
			changeSex = 2;

		if(bs.updUserinfor( userid,changeSex,name,phone,email,address,postalcode,date ) > 0){
			resp.setCharacterEncoding( "utf-8" );
			resp.getWriter().println( "修改成功" );
			resp.getWriter().close();
			return;
		}
	}
}
