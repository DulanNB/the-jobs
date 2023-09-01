<%@page import=" java.util.ArrayList" %>
<%@ page import="com.codewithdulan.thejobs.model.appoinment" %>
<%@ page import="com.codewithdulan.thejobs.model.User" %>
<%@page import=" java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@page import="java.util.*"%>
<% 
 //In case, if User session is not set, redirect to Login page.
if (request.getSession(false).getAttribute("User") == null) {
	String username = (String) request.getSession().getAttribute("User");
    out.println("Username in session: " + username);
    %>
    <jsp:forward page="login.jsp"></jsp:forward>
    <%
} 
%>
<!DOCTYPE html>
<html lang="en">

    <%@include file="setup/header.jsp" %>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <%@include file="setup/sidebar.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <%@include file="setup/profile.jsp" %>

            <!-- Begin Page Content -->
            
            <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Appointments </h1>
                    
                    
			                		<% 
						                // Display the error message if it's available
						                Object suceessMessage = request.getAttribute("successMessage");
						                if (suceessMessage != null) {
						            %>
						            <div class="alert alert-success mt-2">
						                <%= suceessMessage %>
						            </div>
						            <%
						                }
						            %>
			                					    <% 
						                // Display the error message if it's available
						                Object errMessage = request.getAttribute("errorMessage");
						                if (errMessage != null) {
						            %>
						            <div class="alert alert-danger mt-2">
						                <%= errMessage %>
						            </div>
						            <%
						                }
						            %>
             
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"> Appointments</h6>
															<%
								    List<appoinment> appointments = (List<appoinment>) request.getAttribute("appointmentList");
								    if (appointments != null && !appointments.isEmpty()) {
								%>
							
								<%
								    } else {
								        out.println("No appointments to display.");
								    }
								%>
								
														  <% 
								    User loggedInUser = (User) session.getAttribute("User");
								    if (loggedInUser != null && loggedInUser.getRoleID() == 1) { // Assuming user's role_id is 1
								%>
								<a href="createAppointment.jsp" class="btn btn-primary btn-sm float-right" data-toggle="tooltip" data-placement="bottom" title="Add Appointment"><i class="fas fa-plus"></i> Add Appointment</a>
								<% } %>

													
                           
                        </div>
                        
                        <div class="card-body">
                            <div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
								    <thead>
								        <tr>
								            <th>ID</th>
								            <th>Note</th>
								            <th>Country</th>
								            <th>Consultant</th>
								            <th>Appointment Date</th>
								            <th>Action</th>
								        </tr>
								    </thead>
								    <tbody>
								        <%
								            for (appoinment appointment : appointments) {
								        %>
								        <tr>
								            <td><%= appointment.getAppoinmentID() %></td>
								            <td><%= appointment.getAppoinmentNote() %></td>
								            <td><%= appointment.getCountry() %></td>
								            <td><%= appointment.getConsultant() %></td>
								            <td><%= appointment.getAppoinmentDate() %></td>
								            <td>
								            <% 
								   
												    if (loggedInUser != null && loggedInUser.getRoleID() == 1) { // Assuming user's role_id is 1
												%>
												<a method="get" href="<%=request.getContextPath()%>/appoinmentController?action=by_id&id=<%= appointment.getAppoinmentID() %>">
												                    <button class="btn btn-primary">Edit</button>
												                </a>
												<% } %>
												
												<% 
								   
												    if (loggedInUser != null && (loggedInUser.getRoleID() == 2 || loggedInUser.getRoleID() == 1)) { // Assuming user's role_id is 1
												%>
												<a method="get" href="<%=request.getContextPath()%>/appoinmentController?action=delete&id=<%= appointment.getAppoinmentID() %>">
												                    <button class="btn btn-danger">Delete</button>
												                </a>
												<% } %>
								                
								            </td>
								            
								            
								            
								            
								        </tr>
								        <%
								            }
								        %>
								    </tbody>
								</table>

                            </div>
                        </div>
                    </div>

                </div>
            
                
            <!-- End of Main Content -->

            <%@include file="setup/footer.jsp" %>

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <%@include file="setup/footersrc.jsp" %>

</body>

</html>