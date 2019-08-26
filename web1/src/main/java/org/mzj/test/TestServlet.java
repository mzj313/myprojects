package org.mzj.test;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// http://localhost:8380/web1/test?type=session
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 478573569908377986L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		if ("session".equals(type)) {
			testSession(req, resp);
		} else if ("redirect".equals(type)) {
			testRedirect(req, resp);
		} else if ("exception".equals(type)) {
			throw new RuntimeException("test exception");
		} else {
			String tips = "please add parameter 'type'! such as: http://localhost:8380/web1/test?type=session|redirect";
//			System.out.println(tips);
			resp.getWriter().write(tips);
		}
	}

	// 
	private void testSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("sessionid=" + req.getSession().getId());
		String result = sb.toString();
		System.out.println(result);
		resp.getWriter().write(result);
		
		Map<String, HttpSession> sessions = MySessionListener.sessions;
		System.out.println("系统中的sessions:" + sessions.keySet());
	}

	// https://192.168.137.21/web1/test?type=redirect&target_url=https://mzj313.org
	private void testRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String requestURL = req.getRequestURL().toString();
		System.out.println("requestURL=" + requestURL);
		String targetURL = req.getParameter("target_url");
		System.out.println("target_url=" + targetURL);
		if(targetURL != null) {
			resp.sendRedirect(targetURL);
		} else if("".equals(targetURL)) {
			
		} else {
			resp.getWriter().write("miss parameter 'target_url'!");
		}
	}
}
