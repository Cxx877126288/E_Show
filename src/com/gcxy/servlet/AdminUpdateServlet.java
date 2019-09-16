package com.gcxy.servlet;

import com.gcxy.Utils.FileOp;
import com.gcxy.Utils.XmlOpreation;
import com.gcxy.pojo.Admin;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HX
 * @title: AdminUpdateServlet
 * @projectName E_Show
 * @date 2019/8/13  21:17
 */
@WebServlet("/updadmin")
public class AdminUpdateServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "UTF-8" );
		String path = getClass().getClassLoader().getResource("admin.xml").getPath();
		path = FileOp.pathChange( path );
		String adminid = req.getParameter( "adminid" );
		try {
			Admin admin = XmlOpreation.selByAdminID( path, adminid );
			req.setAttribute( "admin",admin );
			req.getRequestDispatcher("/jsp/adminupdate.jsp").forward( req,resp );
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
