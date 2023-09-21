package com.codewithdulan.thejobs.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codewithdulan.thejobs.model.User;
import com.codewithdulan.thejobs.service.userService;

/**
 * Servlet implementation class registerController
 */
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println(request);
		  
		   String message = "";
		   userService service = new userService();

		   User user = User.getInstance();
		   user.setUserName(request.getParameter("userName"));
		   user.setEmail(request.getParameter("email"));
		   user.setContactNo(request.getParameter("contactNo"));
		   user.setUserPassword(request.getParameter("userPassword"));
		   user.setRoleID(1);
		   
		   boolean result;

		    try {
		     
		        if (service.userExists(user.getEmail())) {
		        
		            request.setAttribute("errMessage", "User with the same email already exists!");
		        } else {
		            result = service.addUser(user);
		            if (result) {
		                message = "You have been registered successfully. Please Go back to login";
		            } else {
		                message = "Failed to add the user! User Name:" + user.getUserName();
		            }
		        }
		    } catch (ClassNotFoundException | SQLException e) {
		  
		        System.out.println("message = " + e);
		        message = "An error occurred while processing your request.";
		    }
			
			
			HttpSession session = request.getSession();
		
		   	request.setAttribute("message", message);
		   	System.out.println("message = "+message);
		   	request.getRequestDispatcher("register.jsp").forward(request, response);
	}
	
	

}
