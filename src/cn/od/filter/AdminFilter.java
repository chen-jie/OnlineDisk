package cn.od.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.od.bean.User;
import cn.od.util.Const;

public class AdminFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		User user = (User)((HttpServletRequest)request).getSession().getAttribute(Const.SESSION_USER);
		
		//若是管理员  放行
		if(user.getIsAdmin() == 1){
			chain.doFilter(request, response);
		}
		else{
			response.setContentType("text/html; charset=utf8");
			response.getWriter().print("无访问权限！");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
