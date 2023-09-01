package com.codewithdulan.thejobs.service;

import java.sql.SQLException;
import java.util.List;

import com.codewithdulan.thejobs.dao.userDao;
import com.codewithdulan.thejobs.model.User;

public class userService {
	public  User getSpecifiUserByUserId(int userID) throws ClassNotFoundException, SQLException {

		return userDao.getTheUserByUserId(userID);
	}

	public  List<User> getAllUsers() throws ClassNotFoundException, SQLException{

		return userDao.getAllUsers();
	}

	public  boolean addUser(User user) throws ClassNotFoundException, SQLException {

		return userDao.addUser(user);
	}

	public  boolean updateUser(User user) throws ClassNotFoundException, SQLException {

		return userDao.updateUser(user);
	}

	public  boolean deleteUser(int userID) throws ClassNotFoundException, SQLException {

		return userDao.deleteUser(userID);
	}
}
