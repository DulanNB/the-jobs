package com.codewithdulan.thejobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.codewithdulan.thejobs.model.User;
import com.codewithdulan.thejobs.model.appoinment;

public class appointmentDao {

	public appointmentDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static appoinment getTheAppoinmentsById(int id) throws ClassNotFoundException, SQLException {
		 DBconnector connector = new DBconnectorImplDao();
		    Connection connection = connector.getConnection();

		    String query = "SELECT * FROM appoinments WHERE id = ?";
		    PreparedStatement preparedStatement = connection.prepareStatement(query);
		    preparedStatement.setInt(1, id);

		    ResultSet rs = preparedStatement.executeQuery();
		    appoinment appointment = null;

		    if (rs.next()) {
		        appointment = new appoinment(
		            rs.getInt("id"),
		            rs.getString("appoinment_note"),
		            rs.getInt("user_id"),
		            rs.getInt("consultant_id"),
		            rs.getString("country"),
		            rs.getString("appoinment_date"),
		            rs.getString("appoinment_time"),
		            "dulan" // You might want to fetch this from the result set too
		        );
		    }

		    rs.close();
		    preparedStatement.close();
		    connection.close();

		    return appointment;
	}
	
	public static List<appoinment> getAllAppoinments() throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Select * from appoinments";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);

		List<appoinment> app = new ArrayList();

		while(rs.next()) {
			int consultantId = rs.getInt("consultant_id");
		    String consultantName;
			 
			 if (consultantId == 0) {
			        // Set the default value if consultant_id is 0
			        consultantName = "N/A";
			    } else {
			        // Fetch the consultant's name if consultant_id is not 0
			        consultantName = getConsultantName(consultantId);
			    }
			appoinment appoinments = new appoinment(rs.getInt("id"), rs.getString("appoinment_note"),rs.getInt("user_id"),rs.getInt("consultant_id"),rs.getString("country"),rs.getString("appoinment_date")
					,rs.getString("appoinment_time"),consultantName);
			app.add(appoinments);
		}

		st.close();
		connection.close();
		return app ;
	}

	public static String getConsultantName(int consultantId) throws ClassNotFoundException, SQLException {
	    DBconnector connector = new DBconnectorImplDao();
	    Connection connection = connector.getConnection();

	    String query = "SELECT UserName FROM users WHERE userID = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, consultantId);
	    
	    

	    ResultSet rs = preparedStatement.executeQuery();

	    String consultantName = null;
	    if (rs.next()) {
	        consultantName = rs.getString("UserName");
	    } else {
	        // Set a default value if no result is found
	        consultantName = "Dulan";
	    }
	    
	    System.out.println(consultantName);

	    rs.close();
	    preparedStatement.close();
	    connection.close();

	    return consultantName;
	}

	
	public static List<appoinment> getAllAppoinmentsJobSeeker(int userId) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		 String query = "SELECT * FROM appoinments WHERE user_id = ?";
		 PreparedStatement preparedStatement = connection.prepareStatement(query);
		 preparedStatement.setInt(1, userId);
		 ResultSet rs = preparedStatement.executeQuery();

		List<appoinment> app = new ArrayList();

		while(rs.next()) {
			int consultantId = rs.getInt("consultant_id");
		    String consultantName;
			 
			 if (consultantId == 0) {
			        // Set the default value if consultant_id is 0
			        consultantName = "N/A";
			    } else {
			        // Fetch the consultant's name if consultant_id is not 0
			        consultantName = getConsultantName(consultantId);
			    }
			appoinment appoinments = new appoinment(rs.getInt("id"), rs.getString("appoinment_note"),rs.getInt("user_id"),rs.getInt("consultant_id"),rs.getString("country"),rs.getString("appoinment_date")
					,rs.getString("appoinment_time"),consultantName);
			app.add(appoinments);
		}

		preparedStatement.close();
	    connection.close();
	    return app;
	}
	
	
	public static List<appoinment> getAllAppoinmentsConsultant(int consultant_id) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		 String query = "SELECT * FROM appoinments WHERE consultant_id = ?";
		 PreparedStatement preparedStatement = connection.prepareStatement(query);
		 preparedStatement.setInt(1, consultant_id);
		 ResultSet rs = preparedStatement.executeQuery();

		List<appoinment> app = new ArrayList();

		while(rs.next()) {
			
			int consultantId = rs.getInt("consultant_id");
		    String consultantName;
			 
			 if (consultantId == 0) {
			        // Set the default value if consultant_id is 0
			        consultantName = "N/A";
			    } else {
			        // Fetch the consultant's name if consultant_id is not 0
			        consultantName = getConsultantName(consultantId);
			    }
			
			appoinment appoinments = new appoinment(rs.getInt("id"), rs.getString("appoinment_note"),rs.getInt("user_id"),rs.getInt("consultant_id"),rs.getString("country"),rs.getString("appoinment_date")
					,rs.getString("appoinment_time"),consultantName );
			app.add(appoinments);
		}

		preparedStatement.close();
	    connection.close();
	    return app;
	}
	
	public static boolean addAppoinment(appoinment appoinment) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Insert into appoinments (appoinment_note,user_id,appoinment_date,appoinment_time,country) values (?,?,?,?,?)";
		System.out.println(query);
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, appoinment.getAppoinmentNote());
		ps.setInt(2, appoinment.getUserID());
		ps.setString(3, appoinment.getAppoinmentDate());
		ps.setString(4, appoinment.getAppoinmentTime());
		ps.setString(5, appoinment.getCountry());
		
		System.out.println(ps);

		boolean result = ps.executeUpdate() > 0;
		System.out.println(result);
		ps.close();
		connection.close();
		return result;
	}
	
	public static boolean updateAppoinment(appoinment appoinment) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Update appoinments set appoinment_note=?, user_id=?, consultant_id=?, country=?, appoinment_date=?,appoinment_time=? where id=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, appoinment.getAppoinmentNote());
		ps.setInt(2, appoinment.getUserID());
		ps.setInt(3, appoinment.getConsultantId());
		ps.setString(4, appoinment.getCountry()); // Set consultant_id to index 4
		ps.setString(5, appoinment.getAppoinmentDate());
		ps.setString(6, appoinment.getAppoinmentTime());
		ps.setInt(7, appoinment.getAppoinmentID()); // Set ID to index 7

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		connection.close();
		return result;
	}

	public static boolean deleteAppointment(int appointmentId) throws ClassNotFoundException, SQLException {
	    DBconnector connector = new DBconnectorImplDao();
	    Connection connection = connector.getConnection();

	    String query = "DELETE FROM appoinments WHERE id=?";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, appointmentId);

	    boolean result = ps.executeUpdate() > 0;

	    ps.close();
	    connection.close();
	    return result;


	}


}
