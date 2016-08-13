package com.design.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * http for https 过滤器
 * 
 * @author Guoliang Di
 * @created 2014-07-02
 */
public class UrlRedirect implements Filter {

	@Override
	public void destroy() {
	}

	/**
	 * https 301  重定向 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO DOMAIN需要定义
		String DOMAIN = null;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// 获取请求路径
		String path = httpServletRequest.getRequestURI();
		String queryString = (httpServletRequest.getQueryString() == null ? "" : "?" + httpServletRequest.getQueryString()); // 获取路径中的参数
		String http1 = request.getScheme();
		if ("http".equals(http1)) {   // 判断是否加https
			httpServletResponse.setStatus(301);
			httpServletResponse.setHeader("Location", "https://www." + DOMAIN + path + queryString);
			httpServletResponse.setHeader("Connection", "close");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
