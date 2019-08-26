package org.mzj.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8380/web1/static/mp4/cat.mp4
// 不支持静态资源的访问，tomcat默认的DefaultServlet能处理
public class ResServlet extends HttpServlet {
	private static final long serialVersionUID = 478573569908377987L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ResServlet.doGet " + req.getRequestURL());
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ResServlet.doPost " + req.getRequestURL());
	}
}
