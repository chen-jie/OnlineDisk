package cn.od.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBInfo {

	String url = null;
	String username = null;
	String password = null;
	String driverClass = null;
	
	private static DBInfo db = new DBInfo();

	public static DBInfo getInstance(){
		return db;
	}
	
	private DBInfo() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("db.properties");
		Properties pp = new Properties();
		try {
			pp.load(in);
			url = pp.getProperty("jdbc.url");
			username = pp.getProperty("jdbc.username");
			password = pp.getProperty("jdbc.password");
			driverClass = pp.getProperty("jdbc.driver");
			
			Class.forName(driverClass);
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

	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
