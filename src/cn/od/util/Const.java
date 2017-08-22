package cn.od.util;

import java.util.ArrayList;
import java.util.List;

public class Const {

	public static String SESSION_USER = "user";
	public static String STATIC_URL = "static";
	public static String LOGIN_JSP = "login.jsp";
	public static String LOGIN_URL = "LoginServlet";
	public static String REGISTER_JSP = "register.jsp";
	public static String REGISTER_URL = "RegisterServlet";
	
	public static List<String> OPEN_URL_LIST = new ArrayList<String>();
	static{
		OPEN_URL_LIST.add(REGISTER_JSP);
		OPEN_URL_LIST.add(REGISTER_URL);
		OPEN_URL_LIST.add(LOGIN_URL);
		OPEN_URL_LIST.add(LOGIN_JSP);
		OPEN_URL_LIST.add(STATIC_URL);
	}
}
