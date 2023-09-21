<%@page import=" java.util.ArrayList" %>
<%@ page import="com.codewithdulan.thejobs.model.appoinment" %>
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

				 <div class="card">
			        <h5 class="card-header">Edit Appoinements</h5>
			        <div class="card-body">
									            <%
						    appoinment appointment = (appoinment) request.getAttribute("appointment");
						%>
						<form method="post" action="<%=request.getContextPath()%>/appoinmentController">
						
						    <input type="hidden" name="action" value="admin_update">
														    <%
								    
								    if (appointment != null) {
								%>
								<input type="hidden" name="id" value="<%= appointment.getAppoinmentID() %>">
								
								<div class="form-group">
						        <label for="inputTitle" class="col-form-label">Appointment Note <span class="text-danger">*</span></label>
						        <textarea disabled id="appointment_note" type="text" name="appointment_note" class="form-control"><%= appointment.getAppoinmentNote() %></textarea>
						    </div>
						
						    <div class="form-group">
						        <label for="appointment_date" class="col-form-label">Appointment Date</label>
						        <div class="well">
						            <div id="datetimepicker1" class="input-group date">
						                <input disabled id="appointment_date" data-format="dd/MM/yyyy HH:mm:ss" type="date" name="appointment_date" class="form-control" value="<%= appointment.getAppoinmentDate() %>">
						            </div>
						        </div>
						    </div>
						
						    <div class="form-group">
						        <label for="appointment_time" class="col-form-label">Appointment Time</label>
						        <div class="well">
						            <div id="datetimepicker1" class="input-group date">
						                <input disabled id="appointment_time" data-format="dd/MM/yyyy HH:mm:ss" type="time" name="appointment_time" class="form-control" value="<%= appointment.getAppoinmentTime() %>">
						            </div>
						        </div>
						    </div>
						
						    <div class="form-group">
						        <label for="inputCountry" class="col-form-label mb-10">Country Looking For <span class="text-danger">*</span></label>
						        <select disabled id="country" name="country" class="form-control">
						            <option value="" disabled>Select a country</option>
						            <option value="USA" <%= appointment.getCountry().equals("USA") ? "selected" : "" %>>United States</option>
						            <option value="CAN" <%= appointment.getCountry().equals("CAN") ? "selected" : "" %>>Canada</option>
						            <option value="UK" <%= appointment.getCountry().equals("UK") ? "selected" : "" %>>United Kingdom</option>
						            <option value="SIN" <%= appointment.getCountry().equals("SIN") ? "selected" : "" %>>Singapore</option>
						            <option value="AUS" <%= appointment.getCountry().equals("AUS") ? "selected" : "" %>>Australia</option>
						        </select>
						    </div>
						    
						    <div class="form-group">
			    <label for="consultant" class="col-form-label">Select Consultant</label>
			    <select id="consultant" name="consultat_id" class="form-control">
			        <option value="" disabled>Select a consultant</option>
			        <c:forEach var="consultant" items="${consultants}">
			          <option value="${consultant.userID}" ${appointment.getConsultant().equals(consultant.userName) ? "selected" : ""}>${consultant.userName}</option>
			        </c:forEach>
			    </select>
			</div>
						
						    <div class="form-group mb-3" style="margin-top: 65px">
						        <button type="reset" class="btn btn-warning">Reset</button>
						        <button class="btn btn-success" type="submit">Update</button>
						    </div>
						    
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
						    
								<%
								    } else {
								%>
								<p>Appointment not found or not set.</p>
								<%
								    }
								%>
						    
						   
						</form>

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

<script type="text/javascript">
    $(document).ready(function() {
        $('#datetimepicker1').datetimepicker({
            format: 'DD/MM/YYYY HH:mm:ss',
            icons: {
                time: 'fas fa-clock',
                date: 'fas fa-calendar-alt',
                up: 'fas fa-chevron-up',
                down: 'fas fa-chevron-down',
                previous: 'fas fa-chevron-left',
                next: 'fas fa-chevron-right',
                today: 'fas fa-screenshot',
                clear: 'fas fa-trash',
                close: 'fas fa-times'
            }
        });
    });
</script>

</html>