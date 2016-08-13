package com.design.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UrlForHttp implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 把https重定向为http
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
				// TODO DOMAIN需要重新定义
		        String DOMAIN = null;
		        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		        // 获取域名  
		        String serverName = request.getServerName(); 
		        // 获取请求路径  
		        String path = httpServletRequest.getRequestURI();
		        String queryString = (httpServletRequest.getQueryString() == null ? "" : "?"+httpServletRequest.getQueryString());   // 获取路径中的参数
		        String http1 = request.getScheme();
		        int end = serverName.indexOf(DOMAIN);
		        /**
		         * http://www.yinker.com	OK
		         * https://www.yinker.com	OK
		         * http://yinker.com		OK
		         * http://yinker.com/ddd/	OK
		         * https://yinker.com/ddd/	OK
		         * http://www.yinker.com/ddd/ OK
		         */
		        /**
		         * session 问题
		         */
//		        Cookie cookie = new Cookie("JSESSIONID", httpServletRequest.getSession().getId());
//		        httpServletResponse.addCookie(cookie);
		        if(checkIndex(path) && "http".equals(http1)){  //首页指向https
			        	httpServletResponse.setStatus(301);
			            httpServletResponse.setHeader( "Location", "https://www."+DOMAIN+path+queryString);
			            httpServletResponse.setHeader( "Connection", "close" );
			            return;
		        }

		        if(end==-1 || end == 0){	//判断其他页是否域名为 yinker.com
		        	 httpServletResponse.setStatus(301);
			         httpServletResponse.setHeader( "Location", "http://www."+DOMAIN+path+queryString);
			         httpServletResponse.setHeader( "Connection", "close" );
			         return;
		        }
		        if("https".equals(http1) && !checkIndex(path)){   //对https页面进行http过滤
			        	httpServletResponse.setStatus(301);
			            httpServletResponse.setHeader( "Location", "http://www."+DOMAIN+path+queryString);
			            httpServletResponse.setHeader( "Connection", "close" );
			            return;
		        }
		        chain.doFilter(request, response);
		        
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 判断是否为首页
	 * @param path
	 * @return
	 */
	public static boolean checkIndex(String path){
		boolean res =false;
		if("/".equals(path) || "".equals(path) || "/index.shtml".equals(path) 
				||"/view/user/loanCommentCode.jsp".equals(path)
				||"/registerAssist/registVerfyCode.jsp".equals(path)
				|| null==path){
			res=true;
		}
		return res;
	}

}
