<!-- Sidebar -->
<%@ page import="com.codewithdulan.thejobs.model.User" %>
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">The Jobs <sup></sup></div>
            </a>

         

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Appointments</span></a>
            </li>
            
              <% 
              User user = (User) session.getAttribute("User");
        if (user != null && user.getRoleID() == 1) { 
    %>
    <li class="nav-item">
        <a class="nav-link" href="createAppointment.jsp">
            <i class="fas fa-fw fa-table"></i>
            <span>Create Appointment</span>
        </a>
    </li>
    <% } %>

           
    <% 
       
        if (user != null && user.getRoleID() == 2) { 
    %>
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/userController?action=all">
            <i class="fas fa-fw fa-table"></i>
            <span>User Management</span>
        </a>
    </li>
    <% } %>
    
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">
            <i class="fas fa-sign-out-alt"></i>
            <span>Logout</span>
        </a>
    </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            

        </ul>
        <!-- End of Sidebar -->