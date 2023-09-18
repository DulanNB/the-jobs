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
			        <h5 class="card-header">Add Items</h5>
			        <div class="card-body">
			            <form method="post" action="<%=request.getContextPath()%>/appoinmentController">
							    <div class="form-group">
							        <label for="inputTitle" class="col-form-label">Appointment Note <span class="text-danger">*</span></label>
							        <textarea id="appointment_note" type="text" name="appointment_note" placeholder="Note" class="form-control" required></textarea>
							        <!-- The 'required' attribute makes this field mandatory -->
							    </div>
							
							    <div class="form-group">
							        <label for="appointment_datetime" class="col-form-label">Appointment Date</label>
							        <div class="well">
							            <div id="datetimepicker1" class="input-group date">
							                <input id="appointment_date" data-format="dd/MM/yyyy HH:mm:ss" type="date" name="appointment_date" class="form-control"></input>
							            </div>
							        </div>
							    </div>
							
							    <div class="form-group">
							        <label for="appointment_datetime" class="col-form-label">Appointment Time</label>
							        <div class="well">
							            <div id="datetimepicker1" class="input-group date">
							                <input id="appointment_time" data-format="dd/MM/yyyy HH:mm:ss" type="time" name="appointment_time" class="form-control"></input>
							            </div>
							        </div>
							    </div>
							
							    <div class="form-group">
							        <label for="inputCountry" class="col-form-label mb-10">Country Looking For <span class="text-danger">*</span></label>
							        <select id="country" name="country" class="form-control" required>
							            <option value="" selected disabled>Select a country</option>
							            <option value="USA">United States</option>
							            <option value="CAN">Canada</option>
							            <option value="UK">United Kingdom</option>
							            <option value="SIN">Singapore</option>
							            <option value="AUS">Australia</option>
							        </select>
							    </div>
							
							    <input type="hidden" name="action" value="add"></input>
							
							    <div class="form-group mb-3" style="margin-top:65px">
							        <button type="reset" class="btn btn-warning">Reset</button>
							        <button class="btn btn-success" type="submit" onclick="return validateForm();">Submit</button>
							    </div>
							
							    <!-- Display success and error messages -->
							    <% 
							    // Display the success message if it's available
							    Object successMessage = request.getAttribute("successMessage");
							    if (successMessage != null) {
							    %>
							    <div class="alert alert-success mt-2">
							        <%= successMessage %>
							    </div>
							    <%
							    }
							    %>
							    <% 
							    // Display the error message if it's available
							    Object errorMessage = request.getAttribute("errorMessage");
							    if (errorMessage != null) {
							    %>
							    <div class="alert alert-danger mt-2">
							        <%= errorMessage %>
							    </div>
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

<script>
    function validateForm() {
        var note = document.getElementById("appointment_note").value;
        var date = document.getElementById("appointment_date").value;
        var time = document.getElementById("appointment_time").value;

        if (note === "" || date === "" || time === "") {
            alert("All fields marked with * are required.");
            return false;
        }

        return true;
    }
</script>

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