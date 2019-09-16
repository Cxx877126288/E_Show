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
 * @title: AdminUpdateInforServlet
 * @projectName E_Show
 * @date 2019/8/14  19:29
 * 管理员更新自身的信息
 */
@WebServlet("/updadmininfor")
public class AdminUpdateInforServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "UTF-8" );
		resp.setCharacterEncoding( "UTF-8" );
		String path = getClass().getClassLoader().getResource("admin.xml").getPath();
		path = FileOp.pathChange( path );
		String id = req.getParameter( "id" );
		String sex = req.getParameter( "sex" );
		String name = req.getParameter( "name" );
		String borntime = req.getParameter( "borntime" );
		String email = req.getParameter( "email" );
		String phone = req.getParameter( "phone" );
		String address = req.getParameter( "address" );
		String postalcode = req.getParameter( "postalcode" );

		Admin admin = new Admin();
		admin.setId( id );
		admin.setSex( sex );
		admin.setName( name );
		admin.setPostalcode( postalcode );
		admin.setPhone( phone );
		admin.setAddress( address );
		admin.setEmail( email );
		admin.setBorntime( borntime );
		try {
			if(XmlOpreation.updAdminInfor( path,admin )){
				req.setAttribute( "admin",admin );
				resp.getWriter().println( "修改成功" );
			}else{
				resp.getWriter().println( "修改失败" );
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
