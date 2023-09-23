package com.codewithdulan.thejobs.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codewithdulan.thejobs.model.appoinment;
import com.codewithdulan.thejobs.model.User;
import com.codewithdulan.thejobs.service.appoinmentService;
import com.codewithdulan.thejobs.service.userService;


/**
 * Servlet implementation class appoinmentController
 */
public class appoinmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appoinmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		 HttpSession session = request.getSession();
		    User loggedInUser = (User) session.getAttribute("User");

		    if (loggedInUser != null) {
		        int roleID = loggedInUser.getRoleID();

		        if (action.equals("all") && (roleID == 2)) {
		            getAllApoinments(request, response);
		        } else if (action.equals("job_seeker") && (roleID == 1)) {
		            getAllApoinmentsJobSeeker(request, response);
		        } else if (action.equals("consultant") && (roleID == 3)) {
		            getAllApoinmentsConsultant(request, response);
		        }  else if (action.equals("by_id")) {
		            getAppointmentById(request, response);
		        }
		        else if (action.equals("delete")) {
		        	deleteAppoinment(request, response);
		        }
		      
		        else if (action.equals("admin_by_id") && (roleID == 2)) {
		        	getAppointmentAdminById(request, response);
		        }
		    
		    	else if (action.equals("accept_consultant") && (roleID == 3) ) {
		        	try {
						acceptAppoinment(request, response);
					} catch (ServletException | IOException | MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		         else {
		            response.sendRedirect("unauthorized.jsp"); 
		        }
		    } else {
		       
		        response.sendRedirect("login.jsp"); 
		    }
		
	}
	
	private void getAppointmentAdminById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message ="";
		int id = Integer.parseInt(request.getParameter("id"));

		appoinmentService service = new appoinmentService();
		try {
			appoinment appoinment = service.getAppoinmentByID(id);
			System.out.println(appoinment);
			if(appoinment==null){
				message = "There is no any appointment to show";
			}
			request.setAttribute("appointment", appoinment);
			
			List<User> consultants = userService.getAllConsultants(); // Replace userService with your actual service
	        request.setAttribute("consultants", consultants);
	        
	        System.out.println(consultants);
			
		} catch (ClassNotFoundException | SQLException e) {
			message =e.getMessage();
		}
		
		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("adminEditAppointment2.jsp");
		
		rd.forward(request, response);
	}

	private void getAppointmentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String message ="";
		int id = Integer.parseInt(request.getParameter("id"));

		appoinmentService service = new appoinmentService();
		try {
			appoinment appoinment = service.getAppoinmentByID(id);
			if(appoinment==null){
				message = "There is no any appointment to show";
			}
			request.setAttribute("appointment", appoinment);
			
		} catch (ClassNotFoundException | SQLException e) {
			message =e.getMessage();
		}
		
		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("jobSeekerEditAppointment.jsp");
		
		rd.forward(request, response);
	}

	private void getAllApoinments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message ="";
		appoinmentService service = new appoinmentService();
		try {
			List<appoinment> appoinments = service.getAllAppoinments();

			if(appoinments.isEmpty()){
				message = "There is no any user to show";
			}
			 System.out.println(appoinments);
			request.setAttribute("appointmentList", appoinments);
			
		} catch (ClassNotFoundException | SQLException e) {
			message =e.getMessage();
		}

		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("jobseeker.jsp");
		rd.forward(request, response);
		
	}

	private void getAllApoinmentsJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String message = "";
	    
	    
	    HttpSession session = request.getSession();
	    User loggedInUser = (User) session.getAttribute("User");
	    int userID = loggedInUser.getUserID();
	    
	        appoinmentService service = new appoinmentService();
	        try {
	            List<appoinment> appoinments = service.getAllAppoinmentsJobSeeker(userID);

	            if (appoinments.isEmpty()) {
	                message = "There are no appointments to show for this user";
	            }
	            request.setAttribute("appointmentList", appoinments);
	            System.out.println(appoinments);
	            for (appoinment appointment : appoinments) {
	                System.out.println("Appointment ID: " + appointment.getAppoinmentID());
	                System.out.println("Appointment Note: " + appointment.getAppoinmentNote());
	                // Print more properties as needed
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            message = e.getMessage();
	        }
	   
	      

	    request.setAttribute("message", message);

	    RequestDispatcher rd = request.getRequestDispatcher("jobseeker.jsp");
	    rd.forward(request, response);
		
	}

	private void getAllApoinmentsConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
	    
	    
	    HttpSession session = request.getSession();
	    User loggedInUser = (User) session.getAttribute("User");
	    int userID = loggedInUser.getUserID();
	    
	        appoinmentService service = new appoinmentService();
	        try {
	            List<appoinment> appoinments = service.getAllAppoinmentsConsultant(userID);

	            if (appoinments.isEmpty()) {
	                message = "There are no appointments to show for this user";
	            }

	            request.setAttribute("appointmentList", appoinments);

	        } catch (ClassNotFoundException | SQLException e) {
	            message = e.getMessage();
	        }
	   

	    request.setAttribute("message", message);

	    RequestDispatcher rd = request.getRequestDispatcher("jobseeker.jsp");
	    rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("add")) {
			addAppoinment(request,response);
		}
		else if(action.equals("update"))
		{
			updateAppoinment(request,response);
		}
		else if(action.equals("delete"))
		{
			deleteAppoinment(request,response);
		}
		else if (action.equals("admin_update")) {
        	updateAdminAppoinment(request, response);
        }
		else if (action.equals("accept_consultant")) {
        	try {
				acceptAppoinment(request, response);
			} catch (ServletException | IOException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private void acceptAppoinment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
		HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("User");
        appoinmentService service = new appoinmentService();
        
        int appointmentId = Integer.parseInt(request.getParameter("id"));

        appoinment existingAppointment;
        try {
            existingAppointment = service.getAppoinmentByID(appointmentId);
            
            if (existingAppointment == null) {

                request.setAttribute("errorMessage", "Appointment not found.");
                String redirectURL = "/the-jobs/appoinmentController?action=consultant";
                response.sendRedirect(redirectURL);
                return;
            }
        } catch (ClassNotFoundException | SQLException e) {

            request.setAttribute("errorMessage", "Error fetching appointment.");
            String redirectURL = "/the-jobs/appoinmentController?action=consultant";
            response.sendRedirect(redirectURL);
            return;
        }

        System.out.println( existingAppointment);
        boolean result;
        try {
            result = service.acceptAppointment(existingAppointment);
            if (result) {
                request.setAttribute("successMessage", "Appointment Updated Successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to update appointment.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exception
            request.setAttribute("errorMessage", "Failed to update appointment.");
        }
        request.setAttribute("successMessage", "Appointment Updated Successfully.");
        String encodedMessage = URLEncoder.encode("Appointment Accepted Successfully.", "UTF-8");
        String redirectURL = "/the-jobs/appoinmentController?action=consultant&successMessage="+ encodedMessage;;
        response.sendRedirect(redirectURL);
       
		
	}
	
	private void deleteAppoinment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		   int id =Integer.parseInt(request.getParameter("id"));
		   appoinmentService service = new appoinmentService();
		   try {
			service.deleteAppointment(id);
		   } catch (ClassNotFoundException | SQLException e) {
			   message = e.getMessage();
		   }

		   HttpSession session = request.getSession();
		   session.setAttribute("successMessage", message);
		   
		    
		    User loggedInUser = (User) session.getAttribute("User");
		    int userID = loggedInUser.getUserID();
		    int roleID = loggedInUser.getRoleID();

		 
	        try {
	            List<appoinment> appoinments = service.getAllAppoinmentsJobSeeker(userID);

	            if (appoinments.isEmpty()) {
	                message = "There are no appointments to show for this user";
	            }

	            request.setAttribute("appointmentList", appoinments);

	        } catch (ClassNotFoundException | SQLException e) {
	            message = e.getMessage();
	        }
	        
//	        if(roleID == 1) {
//	        	  request.setAttribute("successMessage", "Appointment Deleted Successfully.");
//	  	        String encodedMessage = URLEncoder.encode("Appointment Deleted Successfully.", "UTF-8");
//	  	        String redirectURL = "/the-jobs/appoinmentController?action=job_seeker&successMessage="+ encodedMessage;;
//	  	        response.sendRedirect(redirectURL);
//	        }
//	        if(roleID == 2) {
//	        	  request.setAttribute("successMessage", "Appointment Deleted Successfully.");
//	  	        String encodedMessage = URLEncoder.encode("Appointment Deleted Successfully.", "UTF-8");
//	  	        String redirectURL = "/the-jobs/appoinmentController?action=consultant&successMessage="+ encodedMessage;;
//	  	        response.sendRedirect(redirectURL);
//	        }
//	        if(roleID == 3) {
//	        	  request.setAttribute("successMessage", "Appointment Deleted Successfully.");
//	  	        String encodedMessage = URLEncoder.encode("Appointment Deleted Successfully.", "UTF-8");
//	  	        String redirectURL = "/the-jobs/appoinmentController?action=all&successMessage="+ encodedMessage;;
//	  	        response.sendRedirect(redirectURL);
//	        }
	    
	        
	        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		    rd.forward(request, response);
		 
		
	}
	
	private void updateAdminAppoinment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("User");
        appoinmentService service = new appoinmentService();
        
        int appointmentId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the existing appointment
        appoinment existingAppointment;
        try {
            existingAppointment = service.getAppoinmentByID(appointmentId);
            
            if (existingAppointment == null) {
                // Handle case when appointment does not exist
                request.setAttribute("errorMessage", "Appointment not found.");
                String encodedMessage = URLEncoder.encode("Appointment Updated Successfully.", "UTF-8");
                String redirectURL = "/the-jobs/appoinmentController?action=admin_by_id&id=" + appointmentId +"&successMessage="+ encodedMessage;
                response.sendRedirect(redirectURL);
                return;
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exception
            request.setAttribute("errorMessage", "Error fetching appointment.");
            String encodedMessage = URLEncoder.encode("Appointment Updated Successfully.", "UTF-8");
            String redirectURL = "/the-jobs/appoinmentController?action=admin_by_id&id=" + appointmentId +"&successMessage="+ encodedMessage;
            response.sendRedirect(redirectURL);
            return;
        }

        // Update the existing appointment with new values
        
        existingAppointment.setConsultantId(Integer.parseInt(request.getParameter("consultat_id")));
        
        System.out.println( existingAppointment);
        boolean result;
        try {
            result = service.updateAdminAppointment(existingAppointment);
            if (result) {
                request.setAttribute("successMessage", "Appointment Updated Successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to update appointment.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exception
            request.setAttribute("errorMessage", "Failed to update appointment.");
        }
        
        String encodedMessage = URLEncoder.encode("Appointment Updated Successfully.", "UTF-8");
        String redirectURL = "/the-jobs/appoinmentController?action=admin_by_id&id=" + appointmentId +"&successMessage="+ encodedMessage;
        response.sendRedirect(redirectURL);
        
		
	}
	

	private void updateAppoinment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("User");
        appoinmentService service = new appoinmentService();
        
        int appointmentId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the existing appointment
        appoinment existingAppointment;
        try {
            existingAppointment = service.getAppoinmentByID(appointmentId);
            
            if (existingAppointment == null) {
                // Handle case when appointment does not exist
                request.setAttribute("errorMessage", "Appointment not found.");
                String encodedMessage = URLEncoder.encode("Appointment Updated Successfully.", "UTF-8");
                String redirectURL = "/the-jobs/appoinmentController?action=by_id&id=" + appointmentId +"&successMessage="+ encodedMessage;
                response.sendRedirect(redirectURL);
                return;
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exception
            request.setAttribute("errorMessage", "Error fetching appointment.");
            String encodedMessage = URLEncoder.encode("Appointment Updated Successfully.", "UTF-8");
            String redirectURL = "/the-jobs/appoinmentController?action=by_id&id=" + appointmentId +"&successMessage="+ encodedMessage;
            response.sendRedirect(redirectURL);
            return;
        }

        // Update the existing appointment with new values
        
        existingAppointment.setAppoinmentNote(request.getParameter("appointment_note"));
        existingAppointment.setAppoinmentDate(request.getParameter("appointment_date"));
        existingAppointment.setAppoinmentTime(request.getParameter("appointment_time"));
        existingAppointment.setCountry(request.getParameter("country"));
        
        boolean result;
        try {
            result = service.updateAppointment(existingAppointment);
            if (result) {
                request.setAttribute("successMessage", "Appointment Updated Successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to update appointment.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exception
            request.setAttribute("errorMessage", "Failed to update appointment.");
        }
        
        String encodedMessage = URLEncoder.encode("Appointment Updated Successfully.", "UTF-8");
        String redirectURL = "/the-jobs/appoinmentController?action=by_id&id=" + appointmentId +"&successMessage="+ encodedMessage;
        response.sendRedirect(redirectURL);
		
	}

	private void addAppoinment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 	HttpSession session = request.getSession();
		    User loggedInUser = (User) session.getAttribute("User");
		    System.out.println(loggedInUser);
		    appoinmentService service = new appoinmentService();
		    
		    System.out.println(loggedInUser.getUserID());
		    System.out.println(request.getParameter("appointment_note"));
		    System.out.println(request.getParameter("appointment_date"));
		   
		    
		    appoinment appointment= new appoinment();
			   appointment.setUserID(loggedInUser.getUserID());
			   appointment.setConsultant(request.getParameter("consultant"));
			   appointment.setAppoinmentNote(request.getParameter("appointment_note"));
			   appointment.setAppoinmentDate(request.getParameter("appointment_date"));
			   appointment.setAppoinmentTime(request.getParameter("appointment_time"));
			   appointment.setCountry(request.getParameter("country"));
			   appointment.setStatus("Pending");
			   
			    boolean result;
			  
				try {
					result = service.addAppoinment(appointment);
					
					
					if(result) {
						request.setAttribute("successMessage", "Appointment Added Successfully.");
						 request.getRequestDispatcher("createAppointment.jsp").forward(request, response);
						
				}
				else {
					request.setAttribute("errorMessage", "Failed to add appointment.");
		            request.getRequestDispatcher("createAppointment.jsp").forward(request, response);
				}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("errorMessage", "Failed to add appointment.");
		            request.getRequestDispatcher("createAppointment.jsp").forward(request, response);
				}
				
	}

}
