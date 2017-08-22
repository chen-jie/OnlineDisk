package cn.od.bean;

public class User {

	private int id;
	private String username;
	private String password;
	private int isAdmin;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", isAdmin=" + isAdmin + "]";
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public User(){
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
