package com.gcxy.servlet;

import com.gcxy.pojo.Account;
import com.gcxy.pojo.Goods;
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
 * @title: AllAccountServlet
 * @projectName E_Show
 * @date 2019/8/4  13:04
 * 用户查询自身的所有订单
 */
@WebServlet("/allaccount")
public class AllAccountServlet extends HttpServlet {
	private AccountService as;
	private GoodsService gs;
	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		as = ac.getBean( "accountService",AccountServiceImpl.class );
		gs = ac.getBean("goodsService",GoodsServiceImpl.class );
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String userid = req.getParameter( "userid" );

		String pagenumber = req.getParameter( "pagenumber" );
		int start = 1,pageSize = 6;

		if(pagenumber != null)
			start = Integer.parseInt( pagenumber );
		int count = as.selByUserid( userid ).size();
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize + 1;
		}

		List<Account> accounts = as.selByUserid( userid,(start - 1) * pageSize , pageSize );
		List<Double> perPrice = new ArrayList<>(  );
		List<Integer> perCategory = new ArrayList<>(  );
		List<Integer> perNumber = new ArrayList<>(  );
		List<String> times = new ArrayList<>(  );
		List<String> flag = new ArrayList<>(  );

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		for(int i = 0; i < accounts.size();i++){
			String goodss = accounts.get( i ).getGoodsid();
			String numbers = accounts.get( i ).getNumber();
			perCategory.add( goodss.split( "," ).length );
			int tNumber = 0;
			double tPrice = 0.0;
			for(int j = 0;j < goodss.split( "," ).length;j++){
				Goods goods = gs.selByID( Integer.parseInt( goodss.split( "," )[j] ) );
				int number = Integer.parseInt( numbers.split( "," )[j] );
				tPrice += number * goods.getGoodsprice();
				tNumber += number;
			}
			perPrice.add( Double.parseDouble( String.format( "%.2f",tPrice ) ) );
			perNumber.add(tNumber);
			if(accounts.get( i ).getExamineflag() == -1){
				flag.add( "审核未通过" );
			}else if(accounts.get( i ).getExamineflag() == 0){
				flag.add( "订单未审核" );
			}else if(accounts.get( i ).getExamineflag() == 1){
				flag.add( "审核已通过" );
			}
			times.add( accounts.get( i ).getTaketime() );
		}
		req.setAttribute( "userid",userid );
		req.setAttribute( "account",accounts );
		req.setAttribute( "price",perPrice );
		req.setAttribute( "category",perCategory );
		req.setAttribute( "number",perNumber );
		req.setAttribute( "time",times );
		req.setAttribute( "flag",flag );
		req.setAttribute( "pagenumber" ,pagenumber );
		req.setAttribute( "count",count );
		req.getRequestDispatcher( "/jsp/allaccount.jsp" ).forward( req,resp );
		return;
	}
}
