package com.gcxy.servlet;

import com.gcxy.mapper.AdminSearchAccount;
import com.gcxy.mapper.AutoSearch;
import com.gcxy.pojo.Account;
import com.gcxy.service.AccountService;
import com.gcxy.service.GoodsService;
import com.gcxy.service.impl.AccountServiceImpl;
import com.gcxy.service.impl.GoodsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HX
 * @title: AdminAllAccountServlet
 * @projectName E_Show
 * @date 2019/8/17  15:28
 * 管理员查询所有的订单
 */
@WebServlet("/selallaccount")
public class AdminAllAccountServlet extends HttpServlet {
	private GoodsService gs;
	private AccountService as;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		as = ac.getBean( "accountService",AccountServiceImpl.class );
		gs = ac.getBean("goodsService",GoodsServiceImpl.class );
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String pagenumber = req.getParameter( "pagenumber" );
		String userid = req.getParameter( "userid" );
		String starttime = req.getParameter( "starttime" );
		String endtime = req.getParameter( "endtime" );
		String flag = req.getParameter( "flag" );

		AdminSearchAccount asa = new AdminSearchAccount();
		if(userid != null && !userid.equals( "" ) && !userid.equals( "null" )){
			asa.setUserid( userid );
		}
		if(starttime != null && !starttime.equals( "" ) && !starttime.equals( "null" )){
			asa.setStarttime( starttime );
		}
		if(endtime != null && !endtime.equals( "" ) && !endtime.equals( "null" )){
			asa.setEndtime( endtime );
		}
		if(flag != null && !flag.equals( "" ) && !flag.equals( "null" )){
			asa.setFlag( Integer.parseInt( flag ) );
		}
		int number = 1;
		if(pagenumber != null && !pagenumber.equals( "" ) && !pagenumber.equals( "null" )){
			number = Integer.parseInt( pagenumber );
		}
		List<Account> accounts = as.searchAccount(asa);
		List<Account> backAccounts = new ArrayList<>(  );
		List<String> times = new ArrayList<>(  );
		List<String> flags = new ArrayList<>(  );
		int index = (number - 1 ) * 5;
		for(int i = 0 ; i < 5;i++){
			if(index < accounts.size()){
				backAccounts.add( accounts.get( index ) );
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
				times.add( accounts.get( index ).getTaketime()  );
				if(accounts.get( index ).getExamineflag() == -1)
					flags.add("未通过");
				else if(accounts.get( index ).getExamineflag() == 0){
					flags.add("未审核");
				}else if(accounts.get( index ).getExamineflag() == 1){
					flags.add("已通过");
				}
				index++;
			}else {
				break;
			}
		}
		int count = 0;
		if(accounts.size() % 5 == 0){
			count = accounts.size() / 5;
		}else {
			count = accounts.size() / 5 + 1;
		}
		req.setAttribute( "userid" ,userid );
		req.setAttribute( "starttime" ,starttime );
		req.setAttribute( "endtime",endtime );
		req.setAttribute( "serflag",flag );

		req.setAttribute( "pagenumber",number );
		req.setAttribute( "count",count );
		req.setAttribute( "accounts",backAccounts );
		req.setAttribute( "time",times );
		req.setAttribute( "flag" ,flags);

		req.getRequestDispatcher( "/jsp/adminallorders.jsp" ).forward( req,resp );
		return;
	}
}
