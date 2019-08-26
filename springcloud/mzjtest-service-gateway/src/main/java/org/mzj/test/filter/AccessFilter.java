package org.mzj.test.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {
	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();
		logger.info("====AccessFilter... url=" + req.getRequestURI().toString());
		String accessToken = req.getParameter("accessToken");
		if (accessToken == null) {
			logger.warn("accessToken is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		logger.info("accessToken=" + accessToken);
		return null;
	}

	public boolean shouldFilter() {
		return true;// 该过滤器是否要执行
	}

	public int filterOrder() {
		return 0;// 路由器执行顺序
	}

	public String filterType() {
		return "pre";// 请求被路由前执行
	}

}
