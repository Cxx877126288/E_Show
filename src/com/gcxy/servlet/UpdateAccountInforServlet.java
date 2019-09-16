package com.gcxy.servlet;

import com.gcxy.pojo.Account;
import com.gcxy.service.AccountService;
import com.gcxy.service.impl.AccountServiceImpl;
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
 * @title: UpdateAccountInforServlet
 * @projectName E_Show
 * @date 2019/8/18  20:20
 * 用户修改订单信息
 */
@WebServlet("/updaccinfor")
public class UpdateAccountInforServlet extends HttpServlet {
	private AccountService as;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		as = ac.getBean( "accountService",AccountServiceImpl.class );
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		resp.setCharacterEncoding( "utf-8"  );
		String accountid = req.getParameter( "accountid" );
		String payway = req.getParameter( "payway" );
		String getway = req.getParameter( "getway" );
		String name = req.getParameter( "name" );
		String address = req.getParameter( "address" );
		String postalcode = req.getParameter( "postalcode" );
		String phone = req.getParameter( "phone" );
		String email = req.getParameter( "email" );
		Account account = new Account();
		account.set_id( accountid );
		account.setName( name );
		account.setPostalcode( Integer.parseInt( postalcode ) );
		account.setEmail( email );
		account.setGetway( getway);
		account.setPayway( payway);
		account.setAddress( address );
		account.setPhone( phone );
		if(as.updAccountInfor( account ) > 0){
			resp.getWriter().println( "修改成功" );
		}else {
			resp.getWriter().println( "修改失败" );
		}

	}
}
