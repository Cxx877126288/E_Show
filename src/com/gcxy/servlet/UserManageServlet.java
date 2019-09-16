package com.gcxy.servlet;

import com.gcxy.mapper.AutoSearch;
import com.gcxy.mapper.UserSearch;
import com.gcxy.pojo.Buyuser;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author HX
 * @title: UserManageServlet
 * @projectName E_Show
 * @date 2019/8/15  10:30
 * 管理员查看所有用户  或  条件查询
 */
@WebServlet("/selalluser")
public class UserManageServlet extends HttpServlet {
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
		String sex = req.getParameter( "sex" );
		String flag = req.getParameter( "flag" );
		String pagenumber = req.getParameter( "pagenumber" );
		UserSearch us = new UserSearch();
		if(userid != null && !userid.equals( "" ) && !userid.equals( "null" )){
			us.setUserid( userid );
		}
		if(sex != null && !sex.equals( "" ) && !sex.equals( "null" )){
			if(sex.equals( "1" ))
				us.setSex( 1 );
			if(sex.equals( "2" ))
				us.setSex( 2 );
		}
		if(flag != null && !flag.equals( "" ) && !flag.equals( "null" )){
			if(flag.equals( "1" ))
				us.setFlag( 1 );
			if(flag.equals( "-1" ))
				us.setFlag( -1 );
		}
		int number = 1;
		if(pagenumber != null && !pagenumber.equals( "" )){
			number = Integer.parseInt( pagenumber );
		}

		List<Buyuser> search = bs.search( us );
		List<Buyuser> users = new ArrayList<>(  );
		int index = (number - 1) * 5;
		for(int i = 0 ; i< 5;i++ ){
			if(index < search.size()){
				users.add( search.get( index ) );
				index++;
			}else{
				break;
			}
		}
		int totalpage = 0;
		if(search.size() % 5 == 0){
			totalpage = search.size() / 5;
		}else {
			totalpage = search.size() / 5 + 1;
		}
		req.setAttribute( "pagenumber",number );
		req.setAttribute( "users",users );
		req.setAttribute( "count" ,totalpage);
		req.getRequestDispatcher( "/jsp/manage.jsp" ).forward( req,resp );
		return;
	}
}
