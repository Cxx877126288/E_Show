package com.gcxy.servlet;

import com.gcxy.Utils.FileOp;
import com.gcxy.Utils.XmlOpreation;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HX
 * @title: AdminUpdatePwdServlet
 * @projectName E_Show
 * @date 2019/8/15  10:06
 * 管理员修改密码
 */
@WebServlet("/updadminpwd")
public class AdminUpdatePwdServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding( "utf-8" );
		String adminid = req.getParameter( "adminid" );
		String newpwd = req.getParameter( "newpwd" );
		String path = getClass().getClassLoader().getResource("admin.xml").getPath();
		path = FileOp.pathChange( path );
		try {
			resp.setCharacterEncoding( "UTF-8" );
			if(XmlOpreation.updAdminsPwd( path,adminid,newpwd )){
				resp.getWriter().println( "修改成功，请重新登录" );
			}else {
				resp.getWriter().println( "修改失败" );
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
