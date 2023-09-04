package com.codewithdulan.thejobs.model;

public class User {
	private int userID;
	   private String userName;
	   private String email;
	   private String contactNo;
	   private String userPassword;
	   private int roleID;


	   private static User instance;
	   
	   private User() {
	    }

	  
	    public static User getInstance() {
	        if (instance == null) {
	            instance = new User();
	        }
	        return instance;
	    }


		public User(String userName, String email, String contactNo, String userPassword, int roleID) {
			super();
			this.userName = userName;
			this.email = email;
			this.contactNo = contactNo;
			this.userPassword = userPassword;
			this.roleID = roleID;
		}

		public User(int userID, String userName, String email, String contactNo, String userPassword,
				int roleID) {
			super();
			this.userID = userID;
			this.email = email;
			this.userName = userName;
			this.contactNo = contactNo;
			this.userPassword = userPassword;
			this.roleID = roleID;
		}

		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getContactNo() {
			return contactNo;
		}

		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}


		public String getUserPassword() {
			return userPassword;
		}

		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}

		public int getRoleID() {
			return roleID;
		}

		public void setRoleID(int roleID) {
			this.roleID = roleID;
		}
}
