package cn.od.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.od.bean.User;

public class UserDao {

	private DBInfo db = DBInfo.getInstance();

	public User findUserById(int id) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {

			ps = conn.prepareStatement("select * from user where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setIsAdmin(rs.getInt(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public boolean findByUsername(String username) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public User addUser(String username, String password) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement("insert into user values(null,?,?,0)");

			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new User(username, password);

	}

	public User login(String username, String password) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {

			ps = conn
					.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setIsAdmin(rs.getInt(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public List<User> findUsers() {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		User user;
		try {

			ps = conn.prepareStatement("select * from user where isadmin = 0");
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setIsAdmin(rs.getInt(4));

				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	public void deleteUserByIds(int[] ids) {
		if (ids == null || ids.length <= 0) {
			return;
		}
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				if (i == ids.length - 1) {
					sb.append("?");
				} else {
					sb.append("?,");
				}
			}
			String in = sb.toString();
			ps = conn.prepareStatement("delete from user where id in (" + in+ ")");
			for(int i = 0 ;i<ids.length;i++){
				ps.setInt(i+1, ids[i]);
			}
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(User user) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("update user set password = ? where id = ?");
			ps.setString(1, user.getPassword());
			ps.setInt(2, user.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
