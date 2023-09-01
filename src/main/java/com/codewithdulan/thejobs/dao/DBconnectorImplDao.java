package com.codewithdulan.thejobs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnectorImplDao implements DBconnector{
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/thejobs";
		String userName ="root";
		String password ="";
		return DriverManager.getConnection(url, userName, password);
	}


}
