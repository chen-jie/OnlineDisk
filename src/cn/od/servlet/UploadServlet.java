package cn.od.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.od.bean.User;
import cn.od.bean.UserFile;
import cn.od.dao.UserFileDao;
import cn.od.util.Const;
import cn.od.util.StringUtil;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 2379426223355652801L;
	private UserFileDao userFileDao = new UserFileDao();
	
	private String UPLOAD_ROOT_PATH = null;
	private DiskFileItemFactory factory;
	private ServletFileUpload stu;
	


	public UploadServlet() {
		super();
	}
	@Override
	public void init() throws ServletException {
		super.init();
		
		//servlet启动时  ，读取配置文件中关于上传的信息
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("ini.properties");
		Properties pp = new Properties();
		try {
			pp.load(in);
			UPLOAD_ROOT_PATH = pp.getProperty("upload.path");
			String tmpPath = pp.getProperty("tmp.path");
			//配置上传临时目录
			factory = new DiskFileItemFactory(1024*1024*10,new File(tmpPath));
			stu = new ServletFileUpload(factory);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
		String from="";
		try {
			List<FileItem> fileItemLists = stu.parseRequest(request);
			for(FileItem fileItem : fileItemLists){
				if(fileItem.isFormField()){
					from = fileItem.getString();
				}else{
					//上传文件名
					String filename = fileItem.getName();
					int index = filename.lastIndexOf("\\");
					if(index != -1) {
					    filename = filename.substring(index+1);
					}
					//上传路径-->D:/upload/当前用户名/****.**
					String root = UPLOAD_ROOT_PATH+user.getUsername();
					//上传文件大小
					long size = fileItem.getSize();
					String sizeString = StringUtil.computeSize(size);
					Timestamp ts = new Timestamp(new Date().getTime());
					
					File file = new File(root,filename);
					
					//解决文件同名
					int cnt = 1;
					while(file.exists()){
						StringBuffer sb = new StringBuffer(filename);
						sb.insert(sb.lastIndexOf("."), "("+cnt+")");
						file = new File(root,sb.toString());
						cnt++;
					}
					//父目录不存在则创建
					if(!file.getParentFile().exists()){
						file.getParentFile().mkdirs();
					}
					
					try {
						fileItem.write(file);
						//上传成功，数据库保存记录
						UserFile userFile = new UserFile();
						userFile.setCreateTime(ts);
						userFile.setFilename(file.getName());
						userFile.setFileSize(sizeString);
						userFile.setIsShared(0);
						userFile.setOwnerId(user.getId());
						userFile.setPath(file.getAbsolutePath());
						userFileDao.save(userFile);
						
						response.sendRedirect(from+"?action=mydisk");
					} catch (Exception e) {
						e.printStackTrace();
						response.getWriter().print("上传出错");
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			response.setContentType("text/html; charset=utf8");
			response.getWriter().print("上传出错!!");
		}
	}

}
