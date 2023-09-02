package com.codewithdulan.thejobs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codewithdulan.thejobs.model.User;
import com.codewithdulan.thejobs.service.userService;

/**
 * Servlet implementation class userController
 */
public class userController extends HttpServlet {

       
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		 HttpSession session = request.getSession();
		    User loggedInUser = (User) session.getAttribute("User");
		    
		    if (loggedInUser != null) {
		        int roleID = loggedInUser.getRoleID();

		        if(action.equals("all") && (roleID == 2)) {
					getAllUsers(request,response);
				}
				else if ( action.equals("by_id") && (roleID == 2)){
					getSpecificUsers(request,response);
				}
		         else {
		            response.sendRedirect("unauthorized.jsp"); 
		        }
		    } else {
		       
		        response.sendRedirect("login.jsp"); 
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		
		
		if(action.equals("add")) {
			addUser(request,response);
		}
		else if(action.equals("update"))
		{
			updateUser(request,response);
		}
		else if(action.equals("delete"))
		{
			deleteUser(request,response);
		}
	}

	private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String message ="";
		userService service = new userService();
		try {
			List<User> users = service.getAllUsers();

			if(users.isEmpty()){
				message = "There is no any user to show";
			}

			request.setAttribute("userList", users);

		} catch (ClassNotFoundException | SQLException e) {
			message =e.getMessage();
		}

		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher("usersList.jsp");
		rd.forward(request, response);
	}

   private void getSpecificUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   String message = "";
	   userService service = new userService();
	   int userID = Integer.parseInt(request.getParameter("id"));

	   User user= new User();
	   try {
		    user = service.getSpecifiUserByUserId(userID);
		    if(user.getUserName().isEmpty() ) {
		    	message = "There is no any user under User Id:" +userID;
		    }
	   } catch (ClassNotFoundException | SQLException e) {
		   message = e.getMessage();
	   }

	    request.setAttribute("message", message);
	    request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("editUser.jsp");
		rd.forward(request, response);

	}

   private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   String message = "";
	   userService service = new userService();

	   User user= new User();
	   user.setUserName(request.getParameter("name"));
	  
	   user.setContactNo(request.getParameter("contactNo"));
	   
	   user.setUserPassword(request.getParameter("userPassword"));
	   user.setRoleID(Integer.parseInt(request.getParameter("roleID")));
	   try {
		boolean result = service.addUser(user);
		 if(result) {
			message = "This user has been added successfully! User Name:" + user.getUserName();
		 }
		 else {
			 message = "Failed to add the user! User Name:" + user.getUserName();
		 }
	   } catch (ClassNotFoundException | SQLException e) {
		   message = e.getMessage();
	   }

	   request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
		rd.forward(request, response);

   }

   private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   String message = "";
	   userService service = new userService();
	   
		System.out.println(request.getParameter("role_id"));

	   User user= new User();
	   int id = Integer.parseInt(request.getParameter("userID"));
	   user.setUserID(Integer.parseInt(request.getParameter("userID")));
	   user.setUserName(request.getParameter("user_name"));
	   user.setEmail(request.getParameter("email"));
	  
	   user.setContactNo(request.getParameter("contact_no"));
	   
	 
	   user.setRoleID(Integer.parseInt(request.getParameter("role_id")));

	   try {
		 boolean result = service.updateUser(user);
	     if(result) {
			message = "The user has been updated successfully! User ID:" + user.getUserID();
		 }
	     else {
	    	 message = "Failed to update the user! User ID:" + user.getUserID();
	     }
	   }
	   catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
	   }

	   String redirectURL = "/the-jobs/userController?action=by_id&id=" + id;
       response.sendRedirect(redirectURL);
   }

   private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

	   String message = "";
	   int userID =Integer.parseInt(request.getParameter("userID"));
	   userService service = new userService();
	   try {
		service.deleteUser(userID);
	   } catch (ClassNotFoundException | SQLException e) {
		   message = e.getMessage();
	   }

	   HttpSession session = request.getSession();
	   session.setAttribute("deleteMessage", message);

	   response.sendRedirect("/new-mobile/getUsers?action=all");
   }


}
