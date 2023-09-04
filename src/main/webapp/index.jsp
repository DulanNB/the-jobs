<%@ page import="com.codewithdulan.thejobs.model.User" %>
<%
    User loggedInUser = (User) session.getAttribute("User");
    String action = "";
    
    if (loggedInUser != null) {
        if (loggedInUser.getRoleID() == 1) {
            action = "action=job_seeker";
        } else if (loggedInUser.getRoleID() == 2) {
            action = "action=all";
        } else if (loggedInUser.getRoleID() == 3) {
            action = "action=consultant";
        }
        
    }
%>
<!DOCTYPE html>
<html>
<head>
    <!-- Other head elements -->

    <script>
        
        document.addEventListener("DOMContentLoaded", function() {

            setTimeout(function() {
               
                var redirectURL = "http://localhost:8080/the-jobs/appoinmentController?" + "<%= action %>";
                window.location.href = redirectURL;
            }, 1); 
        });
    </script>
</head>
<body>
    <!-- Body content -->
</body>
</html>
