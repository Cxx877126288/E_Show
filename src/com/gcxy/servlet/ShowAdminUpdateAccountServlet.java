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
 * @title: ShowAdminUpdateAccountServlet
 * @projectName E_Show
 * @date 2019/8/18  17:47
 * 管理员修改用户订单
 */
@WebServlet("/showupdaccount")
public class ShowAdminUpdateAccountServlet extends HttpServlet {
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

		String account_id = req.getParameter( "account_id" );
		Account account = as.selOneByID( account_id );
		String goodss = account.getGoodsid();
		String numbers = account.getNumber();
		List<Goods> goods = new ArrayList<>(  );
		List<Double> price = new ArrayList<>(  );
		List<Integer> number = new ArrayList<>(  );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

		int count = 0;
		double tPrice = 0.0;

		for(int i = 0 ; i < goodss.split( "," ).length;i++){
			Goods good = gs.selByID( Integer.parseInt( goodss.split( "," )[i] ) );
			price.add( good.getGoodsprice() * Integer.parseInt( numbers.split( "," )[i] ) );
			goods.add( good );
			number.add(  Integer.parseInt( numbers.split( "," )[i] ));
		}

		for(int i = 0; i < goods.size();i++){
			tPrice += goods.get( i ).getGoodsprice() * number.get( i );
			count += number.get( i );
		}
		String flag = "订单未审核";
		if(account.getExamineflag() == -1){
			flag = "审核未通过";
		}else if(account.getExamineflag() == 0){
			flag =  "订单未审核";
		}else if(account.getExamineflag() == 1){
			flag = " 审核已通过";
		}

		req.setAttribute( "account",account );
		req.setAttribute( "totalprice",tPrice );
		req.setAttribute( "totalnumber",count );
		req.setAttribute( "goods",goods );
		req.setAttribute( "price",price );
		req.setAttribute( "number",number );
		req.setAttribute( "flag",flag );
		req.setAttribute( "time",account.getTaketime() );
		req.setAttribute( "extime",account.getExaminetime() );
		req.getRequestDispatcher( "/jsp/updateaccount.jsp" ).forward( req,resp );
	}
}
