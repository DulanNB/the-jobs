<%@ page import="com.codewithdulan.thejobs.model.User" %>
<%
    User loggedInUser = (User) session.getAttribute("User");
    String action = "";
    
    if (loggedInUser != null) {
        if (loggedInUser.getRoleID() == 1) {
            action = "action=job_seeker";
        } else if (loggedInUser.getRoleID() == 2) {
            action = "action=all";
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <!-- Other head elements -->

    <script>
        // Wait for the page to load
        document.addEventListener("DOMContentLoaded", function() {
            // Set the timeout for redirection
            setTimeout(function() {
                // Redirect to the specified URL
                var redirectURL = "http://localhost:8080/the-jobs/appoinmentController?" + "<%= action %>";
                window.location.href = redirectURL;
            }, 1); // Wait for 5 seconds before redirecting
        });
    </script>
</head>
<body>
    <!-- Body content -->
</body>
</html>
