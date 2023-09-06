package com.codewithdulan.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.codewithdulan.thejobs.model.User;

public interface userDao {
	public static User getTheUserByUserId(int userID) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		//String query ="Select * from users where userID = " + userID ;
		//Statement st = connection.createStatement();
		//ResultSet rs = st.executeQuery(query);

		String query ="Select * from users where userID = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, userID);

		ResultSet rs = ps.executeQuery();

		 User user = User.getInstance();
		if(rs.next()) {

			user.setUserID(rs.getInt("userID"));
			user.setEmail(rs.getString("email"));
			user.setUserName(rs.getString("userName"));
			user.setContactNo(rs.getString("contactNo"));
			user.setUserPassword(rs.getString("userPassword"));
			user.setRoleID(rs.getInt("roleID"));
		}

		ps.close();
		connection.close();
		return user;
	}

	public static List<User> getAllUsers() throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Select * from users";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);

		List<User> users = new ArrayList();

		while(rs.next()) {
			User user = new User(rs.getInt("userID"), rs.getString("userName"),rs.getString("email"),rs.getString("contactNo"),rs.getString("userPassword"),rs.getInt("roleID"));
			users.add(user);
		}

		st.close();
		connection.close();
		return users;
	}

	public static boolean addUser(User user) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Insert into users (userName,email,contactNo,userPassword,roleID) values (?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getContactNo());
		ps.setString(4, user.getUserPassword());
		ps.setInt(5, user.getRoleID());

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		connection.close();
		return result;
	}

	public static boolean updateUser(int id, int role_id) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Update users set roleID=? where userID=?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		
		ps.setInt(1, role_id);
		ps.setInt(2, id);
		
		
		System.out.println(id);
		System.out.println(role_id);

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		connection.close();
		return result;
	}

	public static boolean deleteUser(int userID) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

	    String query ="Delete from users where userID=?";
		PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, userID);

	    boolean result = ps.executeUpdate() > 0;
		ps.close();
		connection.close();
		return result;
	}

	public static List<User> getAllConsultants() throws ClassNotFoundException, SQLException {
		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Select * from users where roleID = 3";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);

		List<User> users = new ArrayList();

		while(rs.next()) {
			User user = new User(rs.getInt("userID"), rs.getString("userName"),rs.getString("email"),rs.getString("contactNo"),rs.getString("userPassword"),rs.getInt("roleID"));
			users.add(user);
		}

		st.close();
		connection.close();
		return users;
	}
}
