package com.gcxy.servlet;

import com.gcxy.pojo.Secondca;
import com.gcxy.service.SecondacaService;
import com.gcxy.service.impl.SecondacaServiceImpl;
import net.sf.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author HX
 * @title: InitSelect
 * @projectName E_Show
 * @date 2019/7/21  12:47
 * 在切换大类的时候，同时更改小类的信息
 */
@WebServlet("/initSelect")
public class InitSelect extends HttpServlet {
	private SecondacaService ss;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ss = ac.getBean( "secondcaService",SecondacaServiceImpl.class );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding( "utf-8" );
		String fname = req.getParameter( "fname" );
		List<Secondca> secondcas = ss.selByFollowname( fname );
		JSONArray arr = JSONArray.fromObject( secondcas );
		resp.getWriter().write(arr.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req,resp );
	}
}
