package com.gcxy.servlet;

import com.gcxy.Utils.FileOp;
import com.gcxy.Utils.ValidCode;
import com.gcxy.Utils.XmlOpreation;
import com.gcxy.pojo.Admin;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HX
 * @title: AdminServlet
 * @projectName E_Show
 * @date 2019/8/5  15:51
 * 管理员登陆验证
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getClass().getClassLoader().getResource("admin.xml").getPath();
		path = FileOp.pathChange( path );
		req.setCharacterEncoding( "utf-8" );
		String inCode = req.getParameter( "code" );
		String code = req.getSession().getAttribute( "code" ).toString();
		String adminid = req.getParameter( "adminid" );
		String adminpwd = req.getParameter( "adminpwd" );

		try {
			if(inCode.equals( code )){
				Admin admin = XmlOpreation.selByAdminID( path, adminid );
				if(admin.getPwd().equals( adminpwd )){
					req.setAttribute( "adminID",admin.getId() );
					req.setAttribute( "adminflag",admin.getFlag() );
					req.getRequestDispatcher( "/jsp/adminpage.jsp" ).forward( req,resp );
				}else{
					resp.sendRedirect( "/jsp/adminlogin.jsp"+ "?flag=2&adminid=" + adminid  );
				}
			}else{
				resp.sendRedirect( "/jsp/adminlogin.jsp" + "?flag=1&adminid=" + adminid );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
