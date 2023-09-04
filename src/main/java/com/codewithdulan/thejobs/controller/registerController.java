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
				result = service.addUser(user);
				if(result) {
					message = "This user has been added successfully! User Name:" + user.getUserName();
			}
			else {
					 message = "Failed to add the user! User Name:" + user.getUserName();
			}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("message = "+e);
			}
			
			
			HttpSession session = request.getSession();
		
		   	request.setAttribute("message", message);
		   	System.out.println("message = "+message);
		   	request.getRequestDispatcher("register.jsp").forward(request, response);
	}

}
