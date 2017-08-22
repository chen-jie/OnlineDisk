package cn.od.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.od.bean.User;
import cn.od.dao.UserDao;
import cn.od.util.Const;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -8973681097412844963L;
	private UserDao userDao=new UserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("logout")){
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		}else{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			User user=userDao.login(username, password);
			if(user!=null){
				request.getSession().setAttribute(Const.SESSION_USER, user);
				if(user.getIsAdmin()==1){
					response.sendRedirect("admin");
				}else{
					response.sendRedirect("user");
				}
			}else{
				request.setAttribute("msg", "用户名或密码错误！！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		
			
	}

	

}
