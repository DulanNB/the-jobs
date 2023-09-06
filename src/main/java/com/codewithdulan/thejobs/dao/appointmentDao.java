package com.codewithdulan.thejobs.dao;

import java.sql.Connection;
import javax.mail.*;
import javax.mail.internet.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
		    	int consultantId = rs.getInt("consultant_id");
			    String consultantName;
				 
				 if (consultantId == 0) {
				        // Set the default value if consultant_id is 0
				        consultantName = "N/A";
				    } else {
				        // Fetch the consultant's name if consultant_id is not 0
				        consultantName = getConsultantName(consultantId);
				    }
		        appointment = new appoinment(
		            rs.getInt("id"),
		            rs.getString("appoinment_note"),
		            rs.getInt("user_id"),
		            rs.getInt("consultant_id"),
		            rs.getString("country"),
		            rs.getString("appoinment_date"),
		            rs.getString("appoinment_time"),
		            consultantName ,
		            rs.getString("status")// You might want to fetch this from the result set too
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
					,rs.getString("appoinment_time"),consultantName,rs.getString("status"));
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
					,rs.getString("appoinment_time"),consultantName,rs.getString("status"));
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
			
			int consultantId = rs.getInt("user_id");
		    String consultantName;
			 
			 if (consultantId == 0) {
			        // Set the default value if consultant_id is 0
			        consultantName = "N/A";
			    } else {
			        // Fetch the consultant's name if consultant_id is not 0
			        consultantName = getConsultantName(consultantId);
			    }
			
			appoinment appoinments = new appoinment(rs.getInt("id"), rs.getString("appoinment_note"),rs.getInt("user_id"),rs.getInt("consultant_id"),rs.getString("country"),rs.getString("appoinment_date")
					,rs.getString("appoinment_time"),consultantName , rs.getString("status"));
			app.add(appoinments);
		}

		preparedStatement.close();
	    connection.close();
	    return app;
	}
	
	public static boolean addAppoinment(appoinment appoinment) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		String query = "Insert into appoinments (appoinment_note,user_id,appoinment_date,appoinment_time,country,status) values (?,?,?,?,?,?)";
		System.out.println(query);
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, appoinment.getAppoinmentNote());
		ps.setInt(2, appoinment.getUserID());
		ps.setString(3, appoinment.getAppoinmentDate());
		ps.setString(4, appoinment.getAppoinmentTime());
		ps.setString(5, appoinment.getCountry());
		ps.setString(6, appoinment.getStatus());
		
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
	
	public static boolean updateAdminAppoinment(appoinment appoinment) throws ClassNotFoundException, SQLException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		System.out.println(appoinment.getConsultantId());
		System.out.println(appoinment.getAppoinmentID());
		
		String query = "Update appoinments set consultant_id=? where id=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, appoinment.getConsultantId());
		ps.setInt(2, appoinment.getAppoinmentID()); // Set ID to index 7

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		connection.close();
		return result;
	}
	
	public static boolean acceptAppoinment(appoinment appoinment) throws ClassNotFoundException, SQLException, MessagingException {

		DBconnector connector = new DBconnectorImplDao();
		Connection connection =connector.getConnection();

		
		System.out.println(appoinment.getUserID());
		
		String query = "Update appoinments set status=? where id=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, "Accepted");
		ps.setInt(2, appoinment.getAppoinmentID()); // Set ID to index 7

		boolean result = ps.executeUpdate() > 0;
		if (result) {
	       
	        sendEmail(appoinment);
	    }
		ps.close();
		connection.close();
		return result;
	}
	
	public static void sendEmail(appoinment appoinment) throws MessagingException, SQLException, ClassNotFoundException {
	   
	    String host = "smtp.mailtrap.io";
	    String username = "b50832bf2d0ec2";
	    String password = "3a5ec1fb711409";

	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");

	 
	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	       
	        User user = getUserByUserID(appoinment.getUserID()); 

	   
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
	        message.setSubject("Exciting News: Your Appointment Update!");

	        // Create a more attractive and informative email body
	        int appointmentID = appoinment.getAppoinmentID();
	        String emailBody = "<html><body>"
	                + "<h2 style='color: #007bff;'>Dear " + user.getUserName() + ",</h2>"
	                + "<p>We are thrilled to inform you about your appointment status update!</p>"
	                + "<p>Your appointment with <strong>Appointment ID: APPNT-" + appointmentID + "</strong> has been accepted.</p>"
	                + "<p>Here are the details:</p>"
	                + "<ul>"
	                + "<li><strong>Appointment Date:</strong> " + appoinment.getAppoinmentDate() + "</li>"
	                		+ "<li><strong>Appointment Time:</strong> " + appoinment.getAppoinmentTime() + "</li>"
	                + "<li><strong>Details:</strong> If you didnt recived meeting link via our agent do not hesitate to reach us on 0111111111</li>"
	                + "</ul>"
	                + "<p>Thank you for choosing our service. We look forward to serving you!</p>"
	                + "<p>Best regards,</p>"
	                + "<p>The Jobs Team</p>"
	                + "</body></html>";

	        message.setContent(emailBody, "text/html; charset=utf-8");

	    
	     Transport.send(message);

	        System.out.println("Email sent successfully.");
	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	public static User getUserByUserID(int userID) throws ClassNotFoundException, SQLException {
	    DBconnector connector = new DBconnectorImplDao();
	    Connection connection = connector.getConnection();

	    String query = "SELECT * FROM users WHERE userID = ?";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, userID);

	    ResultSet rs = ps.executeQuery();

	    User user = null;
	    if (rs.next()) {
	        user = new User(
	            rs.getInt("userID"),
	            rs.getString("userName"),
	            rs.getString("email"),
	            rs.getString("contactNo"),
	            rs.getString("userPassword"),
	            rs.getInt("roleID")
	        );
	    }

	    rs.close();
	    ps.close();
	    connection.close();

	    return user;
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
