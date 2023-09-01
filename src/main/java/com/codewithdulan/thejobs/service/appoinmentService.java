package com.codewithdulan.thejobs.service;

import java.sql.SQLException;
import java.util.List;

import com.codewithdulan.thejobs.dao.appointmentDao;
import com.codewithdulan.thejobs.model.appoinment;

public class appoinmentService {

	public appoinmentService() {
		// TODO Auto-generated constructor stub
	}

	public  appoinment getAppoinmentByID(int id) throws ClassNotFoundException, SQLException {

		return appointmentDao.getTheAppoinmentsById(id);
	}

	public  List<appoinment> getAllAppoinments() throws ClassNotFoundException, SQLException{

		return appointmentDao.getAllAppoinments();
	}
	
	public  List<appoinment> getAllAppoinmentsJobSeeker(int userId) throws ClassNotFoundException, SQLException{

		return appointmentDao.getAllAppoinmentsJobSeeker(userId);
	}
	
	public  List<appoinment> getAllAppoinmentsConsultant(int consultantId) throws ClassNotFoundException, SQLException{

		return appointmentDao.getAllAppoinmentsConsultant(consultantId);
	}

	public  boolean addAppoinment(appoinment user) throws ClassNotFoundException, SQLException {

		return appointmentDao.addAppoinment(user);
	}

	
	public  boolean deleteAppointment(int appoinment) throws ClassNotFoundException, SQLException {

		return appointmentDao.deleteAppointment(appoinment);
	}

	public boolean updateAppointment(appoinment user) throws ClassNotFoundException, SQLException {
		return appointmentDao.updateAppoinment(user);
	}

}
