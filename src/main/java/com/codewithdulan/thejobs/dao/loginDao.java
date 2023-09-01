package com.codewithdulan.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.AuthenticationException;

import com.codewithdulan.thejobs.model.User;

public class loginDao {
	public static User authenticateUser(User user) throws ClassNotFoundException, SQLException, AuthenticationException {
		 String email = user.getEmail();
		 String password = user.getUserPassword();

		 DBconnector connector = new DBconnectorImplDao();
		 Connection connection = connector.getConnection();

		 String query = "SELECT email, userPassword, roleID , userID FROM users WHERE email = ? AND userPassword = ?";
		 PreparedStatement ps = connection.prepareStatement(query);
		 ps.setString(1, email);
		 ps.setString(2, password);

		 ResultSet rs = ps.executeQuery();
		 User user1 = null;

		 if (rs.next()) {
		     user1 = new User();
		     user1.setEmail(rs.getString("email"));
		     user1.setUserPassword(rs.getString("userPassword"));
		     user1.setRoleID(rs.getInt("roleID"));
		     user1.setUserID(rs.getInt("userID"));
		 }

		 ps.close();
		 connection.close();
		 return user1;
	}

}
