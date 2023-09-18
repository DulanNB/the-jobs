package com.codewithdulan.thejobs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codewithdulan.thejobs.dao.loginDao;
import com.codewithdulan.thejobs.model.User;

/**
 * Servlet implementation class loginController
 */
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		 User user = User.getInstance();

		user.setEmail(email);
		user.setUserPassword(password);

		loginDao loginDao = new loginDao();

		try
		{
			User userValidate = com.codewithdulan.thejobs.dao.loginDao.authenticateUser(user);

			if(userValidate.getRoleID()==1)
			{
				
				System.out.println(email);

				HttpSession session = request.getSession();

				session.setAttribute("User", userValidate);
				request.setAttribute("userName", userValidate.getUserName());
				request.setAttribute("email", userValidate.getEmail());

				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else if(userValidate.getRoleID()==2)
			{
				System.out.println("Admin");

				HttpSession session = request.getSession();
				session.setAttribute("User", userValidate);
				request.setAttribute("userName", userValidate.getUserName());
				request.setAttribute("email", userValidate.getEmail());

				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else if(userValidate.getRoleID()==3)
			{
				System.out.println("Cosultant");

				HttpSession session = request.getSession();
				session.setAttribute("User", userValidate);
				request.setAttribute("userName", userValidate.getUserName());
				request.setAttribute("email", userValidate.getEmail());

				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else
			{
				System.out.println("Error message = "+userValidate);
				request.setAttribute("errMessage", "Incorrect Login Attempt");

				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		catch (IOException e1)
		{
			request.setAttribute("errMessage", "Incorrect email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		catch (Exception e2)
		{
			request.setAttribute("errMessage", "Incorrect email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
	}
	

}
