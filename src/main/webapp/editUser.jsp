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

				 <div class="card">
			        <h5 class="card-header">Edit Users</h5>
			        
			        <%
						String successMessage = request.getParameter("successMessage");
						if (successMessage != null && !successMessage.isEmpty()) {
						%>
						<div class="alert alert-success">
						    <%= successMessage %>
						</div>
						<script>
						    function removeQueryParameter(key) {
						        var url = window.location.href;
						        var urlParts = url.split("?");
						        if (urlParts.length >= 2) {
						            var params = urlParts[1].split("&");
						            var newParams = [];
						            for (var i = 0; i < params.length; i++) {
						                var param = params[i].split("=");
						                if (param[0] !== key) {
						                    newParams.push(params[i]);
						                }
						            }
						            var newUrl = urlParts[0] + "?" + newParams.join("&");
						            window.history.replaceState({}, document.title, newUrl);
						        }
						    }
						
						    removeQueryParameter("successMessage");
						</script>
						<%
						}
						%>
			        
			        <div class="card-body">
									            <%
									            User User = (User) request.getAttribute("user");
						%>
												<form method="post" action="<%=request.getContextPath()%>/userController">
						
						    <input type="hidden" name="action" value="update">
						
						
						    <input type="hidden" name="userID" value="<%= User.getUserID() %>">
						
						    <div class="form-group">
						        <label for="inputUserName" class="col-form-label">User Name</label>
						        <input id="user_name" type="text" name="user_name" class="form-control" disabled value="<%= User.getUserName() %>">
						    </div>
						
						    <div class="form-group">
						        <label for="inputEmail" class="col-form-label">Email</label>
						        <input id="email" type="email" name="email" class="form-control" disabled value="<%= User.getEmail() %>">
						    </div>
						
						    <div class="form-group">
						        <label for="inputContactNo" class="col-form-label">Contact No</label>
						        <input id="contact_no" type="text" name="contact_no" class="form-control" disabled value="<%= User.getContactNo() %>">
						    </div>
						
						    <div class="form-group">
						        <label for="inputRoleID" class="col-form-label">Role ID</label>
						        <select id="role_id" name="role_id" class="form-control" >
						            <option value="1" <% if (User.getRoleID() == 1) { %> selected <% } %>>Job Seeker</option>
						            <option value="2" <% if (User.getRoleID() == 2) { %> selected <% } %>>Admin</option>
						            <option value="3" <% if (User.getRoleID() == 3) { %> selected <% } %>>Consultant</option>
						        </select>
						    </div>
						
						  
						    	<% 
								    User loggedInUser = (User) session.getAttribute("User");
								    if (loggedInUser != null && loggedInUser.getRoleID() == 2) { 
								%>
								 <div class="form-group mb-3" style="margin-top: 65px">
						        <button type="reset" class="btn btn-warning">Reset</button>
						        <button class="btn btn-success" type="submit">Update</button>
						    </div>
								<% } %>
						    
						   
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