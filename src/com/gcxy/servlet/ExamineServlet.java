package com.gcxy.servlet;

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
import java.util.Date;

/**
 * @author HX
 * @title: ExamineServlet
 * @projectName E_Show
 * @date 2019/8/19  12:51
 * 管理员审核订单
 */
@WebServlet("/examine")
public class ExamineServlet extends HttpServlet {
	private AccountService as;
	private GoodsService gs;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		as = ac.getBean( "accountService",AccountServiceImpl.class );
		gs = ac.getBean( "goodsService", GoodsServiceImpl.class );
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String flag = req.getParameter( "flag" );
		if(flag != null && !flag.equals( "" ) && !flag.equals( "null" )){
			String accountid = req.getParameter( "accountid" );
			String adminid = req.getParameter( "adminid" );
			String infor = req.getParameter( "infor" );
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			resp.setCharacterEncoding( "UTF-8" );
			if(as.examineOrder( adminid,accountid,Integer.parseInt( flag ),infor,sdf.format( new Date(  ) ) ) > 0){
				Account account = as.selOneByID( accountid );
				String goodsids = account.getGoodsid();
				String numbers = account.getNumber();
				String[] goodsid = goodsids.split( "," );
				String[] number = numbers.split( "," );
				for(int i = 0 ;i < goodsid.length;i++){
					gs.examineSucceess( Integer.parseInt( goodsid[i] ),Integer.parseInt( number[i] ) );
				}
				resp.getWriter().println( "审核成功" );
			}else{
				resp.getWriter().println( "审核失败" );
			}

		}
	}
}
