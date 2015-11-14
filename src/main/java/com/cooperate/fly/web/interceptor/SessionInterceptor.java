package com.cooperate.fly.web.interceptor;

import java.io.PrintWriter;

import javax.print.attribute.standard.MediaSize.Other;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cooperate.fly.util.Constant;

public class SessionInterceptor implements HandlerInterceptor{
	
	private Logger log=Logger.getLogger(getClass());

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri=request.getRequestURI();
		log.info("uri="+uri);
		uri=uri.replaceFirst(request.getContextPath(), "");
		
		if (!uri.startsWith("/page")) {
			return true;
		}
		
		if (request.getSession().getAttribute(Constant.USER_SESSION_KEY)==null) {
			//未登录
			PrintWriter out=response.getWriter();
			StringBuilder builder=new StringBuilder();
			builder.append("<script type=\" text/javascript \" charset=\"UTF-8\">");
			builder.append("window.top.location.href=\"");
			builder.append(request.getContextPath());
			builder.append("/\";</script>");
			out.print(builder.toString());
			out.close();
			return false;
		}else{
			return true;
		}
	}

}
