package cn.od.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.od.bean.User;
import cn.od.bean.UserFile;
import cn.od.dao.UserDao;
import cn.od.dao.UserFileDao;
import cn.od.util.Const;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 5638849060142637816L;
	private UserDao userDao = new UserDao();
	private UserFileDao userFileDao = new UserFileDao();

	public AdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
		
		//根君action判断当前用户的操作
		String action = request.getParameter("action");
		//跳转到主页，所有共享
		if (action == null || action.equals("") || action.equals("index")) {
			String filename = request.getParameter("filename");
			List<UserFile> fileList;
			if(filename != null && !filename.equals("")){
				fileList = userFileDao.findSharedFileWithName(filename);
			}else{
				fileList = userFileDao.findSharedFile();
			}
			request.setAttribute("fileList", fileList);
			request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request, response);
		}else if(action.equals("all")){
			String filename = request.getParameter("filename");
			List<UserFile> fileList;
			if(filename != null && !filename.equals("")){
				fileList = userFileDao.findAllWithName(filename);
			}else{
				fileList = userFileDao.findAll();
			}
			request.setAttribute("fileList", fileList);
			request.getRequestDispatcher("/WEB-INF/admin/all.jsp").forward(request, response);
		}else if(action.equals("myshare")){
			//跳转到 我的共享
			List<UserFile> fileList = userFileDao.findMySharedFile(user.getId());
			request.setAttribute("fileList", fileList);
			request.getRequestDispatcher("/WEB-INF/admin/myshare.jsp").forward(request, response);
			
		}else if(action.equals("mydisk")){
			//跳转到 我的网盘
			List<UserFile> fileList = userFileDao.findFileListByOwnerId(user.getId());
			request.setAttribute("fileList", fileList);
			request.getRequestDispatcher("/WEB-INF/admin/mydisk.jsp").forward(request, response);
			
		}else if(action.equals("share")){
			//根据file_ID分享文件
			int id = Integer.parseInt(request.getParameter("id"));
			UserFile userFile = userFileDao.findUserFileById(id);
			//判断文件的所有者,文件主人才能分享
			if(user.getId() == userFile.getOwnerId()){
				userFile.setIsShared(1);
				userFileDao.update(userFile);
			}
			response.sendRedirect("admin?action=mydisk");
			
		}else if(action.equals("cancelShare")){
			//根据file_ID分享文件
			int id = Integer.parseInt(request.getParameter("id"));
			UserFile userFile = userFileDao.findUserFileById(id);
			//判断文件的所有者,文件主人才能分享
			if(user.getId() == userFile.getOwnerId()){
				userFile.setIsShared(0);
				userFileDao.update(userFile);
			}
			response.sendRedirect("admin?action=myshare");
			
		}else if(action.equals("delete")){
			
			//将字符串数组转为int 数组
			String[] vs = request.getParameterValues("ids");
			String from = request.getParameter("from");
			int[] ids = strArr2intArr(vs);
			userFileDao.deleteByIds(ids);
			response.sendRedirect("admin?action="+from);
		
		}else if(action.equals("userList")){
			List<User> userList = userDao.findUsers();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/WEB-INF/admin/user_list.jsp").forward(request, response);
		}else if(action.equals("deleteUser")){
			String[] vs = request.getParameterValues("ids");
			int[] ids = strArr2intArr(vs);
			userDao.deleteUserByIds(ids);
			response.sendRedirect("admin?action=userList");
		}else if(action.equals("showUser")){
			
			int ownerId = Integer.parseInt(request.getParameter("id"));
			List<UserFile> fileList = userFileDao.findFileListByOwnerId(ownerId);
			User theUser = userDao.findUserById(ownerId);
			request.setAttribute("fileList", fileList);
			request.setAttribute("theUser", theUser);
			request.getRequestDispatcher("/WEB-INF/admin/show_user.jsp").forward(request, response);
		}else if(action.equals("edit")){
			request.getRequestDispatcher("/WEB-INF/admin/edit.jsp").forward(request, response);
		}
		else if(action.equals("editSubmit")){
			
			String ori_psw = request.getParameter("password");
			String new_psw = request.getParameter("password1");
			
			if(ori_psw.equals(user.getPassword())){
				user.setPassword(new_psw);
				userDao.update(user);
				request.setAttribute("msgSuccess", "密码修改成功！");
				request.getRequestDispatcher("/WEB-INF/admin/edit.jsp").forward(request, response);
			}else{
				request.setAttribute("msgFail", "原密码错误！！修改失败！！");
				request.getRequestDispatcher("/WEB-INF/admin/edit.jsp").forward(request, response);
			}
		}
			
	}

	
	public int[] strArr2intArr(String[] arr){
		if(arr == null || arr.length == 0)
			return null;
		int[] intArr = new int[arr.length];
		for(int i = 0; i<arr.length;i++){
			intArr[i] = Integer.parseInt(arr[i]);
		}
		return intArr;
	}
}
