package com.codewithdulan.thejobs.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBconnector {
	public Connection getConnection() throws ClassNotFoundException , SQLException;
}
