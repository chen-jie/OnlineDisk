package cn.od.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.od.bean.User;
import cn.od.util.Const;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI();
		if(parseUrl(url)){
			//是公开url，放行
			chain.doFilter(request, response);
			return;
		}
		User user = (User)req.getSession().getAttribute(Const.SESSION_USER);
		//session中不存在user,则拦截，跳转到登录界面
		if(user==null){
			((HttpServletResponse)response).sendRedirect("login.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}

	private boolean parseUrl(String url) {
		for(String open_url : Const.OPEN_URL_LIST){
			//如果URL是公开的URL，返回true.
			if(url.indexOf(open_url)>=0)
				return true;
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
