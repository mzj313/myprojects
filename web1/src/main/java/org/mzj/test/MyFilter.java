package org.mzj.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		System.out.println("MyFilter.doFilter ->");
//		res.setHeader("Accept-Ranges", "bytes");
		res.setHeader("Accept-Ranges", "none");
		chain.doFilter(req, res);
		System.out.println("MyFilter.doFilter -|");
	}

	public void init(FilterConfig config) throws ServletException {
	}

}
