package com.gcxy.servlet;

import com.gcxy.Utils.ValidCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author HX
 * @title: ValidCodeServlet
 * @projectName E_Show
 * @date 2019/8/6  13:10
 * 验证码的生成
 @
 */
@WebServlet("/valid")
public class ValidCodeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet( req, resp );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream os = resp.getOutputStream();
		String code = ValidCode.getCode( os );
		HttpSession session = req.getSession();
		session.setAttribute( "code",code );
	}
}
