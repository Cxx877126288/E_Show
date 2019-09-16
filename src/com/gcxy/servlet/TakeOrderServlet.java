package com.gcxy.servlet;

import com.gcxy.pojo.Account;
import com.gcxy.pojo.Buyuser;
import com.gcxy.service.AccountService;
import com.gcxy.service.BuycarService;
import com.gcxy.service.BuyuserService;
import com.gcxy.service.GoodsService;
import com.gcxy.service.impl.AccountServiceImpl;
import com.gcxy.service.impl.BuycarServiceImpl;
import com.gcxy.service.impl.BuyuserServiceImpl;
import com.gcxy.service.impl.GoodsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author HX
 * @title: TakeOrderServlet
 * @projectName E_Show
 * @date 2019/8/2  12:24
 * 用户下单
 */
@WebServlet("/takeorder")
public class TakeOrderServlet extends HttpServlet {
	private AccountService as;
	private GoodsService gs;
	private BuycarService bcs;
	private BuyuserService bs;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		as = ac.getBean( "accountService",AccountServiceImpl.class);
		gs = ac.getBean( "goodsService",GoodsServiceImpl.class);
		bcs = ac.getBean( "buycarService",BuycarServiceImpl.class);
		bs = ac.getBean( "buyuserService",BuyuserServiceImpl.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );

		String payway = req.getParameter( "payway" );
		String getway = req.getParameter( "getway" );
		String userid = req.getParameter( "userid" );
		String username = req.getParameter( "name" );
		String goodsid = req.getParameter( "goodsid" );
		String number = req.getParameter( "number" );
		String address = req.getParameter( "address" );
		String postalcode = req.getParameter( "postalcode" );
		String phone = req.getParameter( "phone" );
		String email = req.getParameter( "email" );
		String time = req.getParameter( "time" );
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date = null;
		try {
			date = df.parse( time );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Account account = new Account();
		String s = "";
		Random random = new Random(  );
		for(int i = 0; i < 5;i++){
			s += random.nextInt( 10 );
		}
		Buyuser user = bs.selByID( userid );
		account.set_id( s + date.getTime() );
		account.setGoodsid( goodsid );
		account.setNumber( number);
		account.setUserid( userid );
		account.setPayway( payway );
		account.setGetway( getway );
		account.setAddress( address );
		account.setPhone( phone );
		account.setEmail( email );
		account.setPostalcode( Integer.parseInt( postalcode ) );
		account.setExamineflag( 0 );
		account.setTaketime( sdf.format( date ) );
		account.setName( username );
		if(as.insNewAccount( account ) > 0){
			String[] split_goods = goodsid.split( "," );
			String[] spllit_number = number.split( "," );
			for(int i = 0;i < split_goods.length;i++){
				gs.updGoodsNumber( split_goods[i],spllit_number[i] );
				bcs.delByUseridAndGoodsid( userid,Integer.parseInt( split_goods[i]  ));
			}

			resp.setCharacterEncoding( "UTF-8" );
			String name = user.getUsername();
			name = new String(name.getBytes("utf-8"),"iso-8859-1");
			String url = "/jsp/ownercenter.jsp?userid=" +  userid + "&username=" + name;
			resp.sendRedirect( url );
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}
}
